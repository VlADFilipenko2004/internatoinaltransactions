package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import com.internationaltransactions.model.AppUser;
import com.internationaltransactions.model.Shipping;
import com.internationaltransactions.model.enums.Role;
import com.internationaltransactions.model.enums.ShippingStatus;
import com.internationaltransactions.model.enums.ShippingType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shippings")
public class ShippingCont extends Main {
    @GetMapping
    public String products(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("shippingTypes", ShippingType.values());
        model.addAttribute("products", getUser().getProducts());
        AppUser user = getUser();
        if (user.getRole() == Role.USER) {
            model.addAttribute("shippings", getUser().getShippings());
        } else {
            model.addAttribute("shippings", shippingRepo.findAll());
        }
        return "shippings";
    }

    @PostMapping("/add")
    String add(@RequestParam String country, @RequestParam String dateAndTimeSending, @RequestParam String dateAndTimeArrival, @RequestParam ShippingType type, @RequestParam Long productId) {
        shippingRepo.save(new Shipping(country, dateAndTimeSending, dateAndTimeArrival, type, productRepo.getReferenceById(productId), getUser()));
        return "redirect:/shippings";
    }

    @GetMapping("/{id}/complete")
    String add(@PathVariable Long id) {
        Shipping shipping = shippingRepo.getReferenceById(id);
        shipping.setStatus(ShippingStatus.COMPLETED);
        shippingRepo.save(shipping);
        return "redirect:/shippings";
    }


}