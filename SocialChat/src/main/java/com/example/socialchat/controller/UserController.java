package com.example.socialchat.controller;

import com.example.socialchat.domain.Role;
import com.example.socialchat.domain.User;
import com.example.socialchat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model)
    {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @GetMapping("/edit/{user}")
    public String userEditForm(@PathVariable User user, Model model)
    {
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @GetMapping("/delete/{user}")
    public String deleteUser(@PathVariable User user) {
        userService.deleteById(user);
        return "redirect:/user";
    }

    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String,String> form,
            @RequestParam("userId") User user)
    {
        userService.saveUser(user, username, form);
        return "redirect:/user";
    }

    @GetMapping("/info/{id}")
    public String infoUser(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "userInfo";

    }

}
