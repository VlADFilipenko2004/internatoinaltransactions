package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginCont extends Main {

    @GetMapping
    public String login() {
        return "login";
    }
}
