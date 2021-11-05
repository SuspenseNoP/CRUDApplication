package by.ivanovsky.crudapp.controller;

import by.ivanovsky.crudapp.Model.User;
import by.ivanovsky.crudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model)
    {
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "user-list";
    }
    @GetMapping("/user-info/{id}")
    public String infoUserForm(Model model, @PathVariable ("id") Long id) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-info";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user)
    {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user)
    {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUserById(@PathVariable Long id)
    {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(Model model, @PathVariable ("id") Long id)
    {
        User user = userService.findById(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user)
    {
        userService.saveUser(user);
        return "redirect:/users";
    }

}
