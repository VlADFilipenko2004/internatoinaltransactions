package com.internationaltransactions.controller.main;

import com.internationaltransactions.model.AppUser;
import com.internationaltransactions.repo.ProductRepo;
import com.internationaltransactions.repo.ShippingRepo;
import com.internationaltransactions.repo.TransactionRepo;
import com.internationaltransactions.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

public class Main {

    @Autowired
    protected UserRepo userRepo;
    @Autowired
    protected ProductRepo productRepo;
    @Autowired
    protected ShippingRepo shippingRepo;
    @Autowired
    protected TransactionRepo transactionRepo;
    @Value("${upload.img}")
    protected String uploadImg;

    protected void getCurrentUserAndRole(Model model) {
        model.addAttribute("role", getRole());
        model.addAttribute("user", getUser());
    }

    protected AppUser getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return userRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getRole() {
        AppUser appUser = getUser();
        if (appUser == null) return "NOT";
        return appUser.getRole().toString();
    }

    protected String getDateNow() {
        String date = LocalDateTime.now().toString();
        return date.substring(0, 10) + " " + date.substring(11, 19);
    }

    protected static float round(float value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }
}