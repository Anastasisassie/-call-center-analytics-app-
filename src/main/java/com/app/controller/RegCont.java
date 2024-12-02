package com.app.controller;

import com.app.controller.global.Global;
import com.app.model.User;
import com.app.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Global {

    @GetMapping
    public String reg(Model model) {
        addAttributes(model);
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password) {
        if (userRepo.findAll().isEmpty() || userRepo.findAll().size() == 0) {
            userRepo.save(new User(username, password, Role.ADMIN));
            return "redirect:/login";
        }

        if (userRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            addAttributes(model);
            return "reg";
        }

        userRepo.save(new User(username, password, Role.USER));

        return "redirect:/login";
    }
}