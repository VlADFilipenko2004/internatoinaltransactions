package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import com.internationaltransactions.model.Transaction;
import com.internationaltransactions.model.enums.TransactionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/transactions")
public class TransactionCont extends Main {
    @GetMapping
    public String transactions(Model model) {
        getCurrentUserAndRole(model);
        model.addAttribute("transactionWaiting", transactionRepo.findAllByStatus(TransactionStatus.WAITING));
        model.addAttribute("transactionCompleted", transactionRepo.findAllByStatus(TransactionStatus.COMPLETED));
        return "transactions";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @RequestParam int price, @RequestParam String sending, @RequestParam String arrival) {
        Transaction transaction = transactionRepo.getReferenceById(id);
        transaction.setPrice(price);
        transaction.setSending(sending);
        transaction.setArrival(arrival);
        transactionRepo.save(transaction);
        return "redirect:/transactions";
    }

    @GetMapping("/{id}/pay")
    public String pay(Model model, @PathVariable Long id) {
        LocalDateTime now = LocalDateTime.now();
        long rand = (long) ((Math.random() * 5) + 1);
        now = now.plusSeconds(rand);
        model.addAttribute("random", rand);

        Transaction transaction = transactionRepo.getReferenceById(id);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transaction.setDateAndTime(now.toString().substring(0, 10) + " " + now.toString().substring(11, 19));
        transactionRepo.save(transaction);

        return "waiting";
    }


}