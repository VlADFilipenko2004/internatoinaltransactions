package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import com.internationaltransactions.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductCont extends Main {
    @GetMapping
    public String products(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("products", getUser().getProducts());
        return "products";
    }

    @PostMapping("/add")
    String add(@RequestParam String name, @RequestParam int weight) {
        productRepo.save(new Product(name, weight, getUser()));
        return "redirect:/products";
    }

    @PostMapping("/{id}/edit")
    String edit(@RequestParam String name, @RequestParam int weight, @PathVariable Long id) {
        Product product = productRepo.getReferenceById(id);
        product.set(name, weight);
        productRepo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/{id}/delete")
    String delete(@PathVariable Long id) {
        productRepo.deleteById(id);
        return "redirect:/products";
    }
}