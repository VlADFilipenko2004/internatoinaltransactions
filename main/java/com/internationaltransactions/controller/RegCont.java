package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import com.internationaltransactions.model.AppUser;
import com.internationaltransactions.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Main {

    @GetMapping
    public String reg() {
        return "reg";
    }

    @PostMapping
    public String regUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String fio, @RequestParam String tel, @RequestParam String email) {
        if (userRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            getCurrentUserAndRole(model);
            return "reg";
        }

        AppUser user = new AppUser(username, password, fio, tel, email);

        if (userRepo.findAll().isEmpty()) {
            user.setRole(Role.ADMIN);
        }

        userRepo.save(user);

        return "redirect:/login";
    }
}
