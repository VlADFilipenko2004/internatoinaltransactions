package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import com.internationaltransactions.model.AppUser;
import com.internationaltransactions.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserCont extends Main {

    @GetMapping
    public String users(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("roles", Role.values());
        model.addAttribute("users", userRepo.findAllByOrderByRole());
        return "users";
    }

    @PostMapping("/{id}/edit")
    public String editUser(@PathVariable long id, @RequestParam Role role) {
        AppUser user = userRepo.getReferenceById(id);
        user.setRole(role);
        userRepo.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable long id) {
        userRepo.deleteById(id);
        return "redirect:/users";
    }
}
