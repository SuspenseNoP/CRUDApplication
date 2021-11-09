package com.example.socialchat.controller;

import com.example.socialchat.domain.User;
import com.example.socialchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String registration()
    {
        return "registration";
    }

    @PostMapping
    public String addUser(User user, Map<String,Object> model)
    {

        if (!userService.addUser(user))
        {
            model.put("message" , "User exist!");
            return "registration";
        }

        return "redirect:/login";
    }
}
