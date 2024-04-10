package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import com.internationaltransactions.model.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/profile")
public class ProfileCont extends Main {

    @GetMapping
    public String profile(Model model) {
        getCurrentUserAndRole(model);
        return "profile";
    }

    @PostMapping("/edit")
    public String editProfile(@RequestParam String fio, @RequestParam String email, @RequestParam String tel) {
        AppUser user = getUser();
        user.setFio(fio);
        user.setTel(tel);
        user.setEmail(email);
        userRepo.save(user);
        return "redirect:/index";
    }

    @PostMapping("/photo")
    public String photoProfile(Model model, @RequestParam MultipartFile photo) {
        String resultPhoto = "";
        try {
            if (photo != null && !Objects.requireNonNull(photo.getOriginalFilename()).isEmpty()) {
                String uuidFile = UUID.randomUUID().toString();
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) uploadDir.mkdir();
                resultPhoto = "user/" + uuidFile + "_" + photo.getOriginalFilename();
                photo.transferTo(new File(uploadImg + "/" + resultPhoto));
            }
        } catch (IOException e) {
            model.addAttribute("message", "Некорректные данные!");
            getCurrentUserAndRole(model);
            return "profile";
        }
        AppUser user = getUser();
        user.setPhoto(resultPhoto);
        userRepo.save(user);
        return "redirect:/index";
    }
}
