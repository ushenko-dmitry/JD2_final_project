package ru.mail.dimaushenko.webmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mail.dimaushenko.service.model.AddUserDTO;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String showUsers(){
        return "users";
    }
    
    @GetMapping("/add")
    public String addUser(Model model) {
        model.addAttribute("new_user", new AddUserDTO());
        return "user_add";
    }

}
