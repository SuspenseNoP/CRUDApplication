package com.example.socialchat.controller;


import com.example.socialchat.domain.Message;
import com.example.socialchat.domain.User;
import com.example.socialchat.repository.MsgRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

@Controller
public class MsgController {

    private final MsgRepository msgRepository;

    public MsgController(MsgRepository msgRepository) {
        this.msgRepository = msgRepository;
    }

    @GetMapping("/")
    public String home(Map<String, Object> model)
    {
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model, @RequestParam(required = false, defaultValue = "") String filter)
    {
        Iterable<Message> messages = msgRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = msgRepository.findByTag(filter);
        } else {
            messages = msgRepository.findAll();
        }

        model.addAttribute("messages", messages );
        model.addAttribute("filter",filter);
        return "main";
    }

    @PostMapping("/main")
    public String addMsg(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag, Map<String,Object> model)
    {
        Message message = new Message(text, tag,user);
        msgRepository.save(message);
        Iterable<Message> messages = msgRepository.findAll();

        model.put("messages", messages );
        return "main";

    }

    @GetMapping("/user-messages/{user}")
    public String userMessages(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ) {
        Set<Message> messages = user.getMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag
    ){
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }



            msgRepository.save(message);
        }

        return "redirect:/user-messages/" + user;
    }

}
