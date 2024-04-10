package com.internationaltransactions.controller;

import com.internationaltransactions.controller.main.Main;
import com.internationaltransactions.model.Transaction;
import com.internationaltransactions.model.enums.TransactionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Main {
    @GetMapping
    public String stats(Model model) {
        getCurrentUserAndRole(model);
        List<Transaction> transactions = transactionRepo.findAll();
        model.addAttribute("transactions", transactions);

        int completed = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getStatus() == TransactionStatus.COMPLETED) return i + transaction.getPrice();
            return i;
        }, Integer::sum);
        int waiting = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getStatus() == TransactionStatus.WAITING) return i + transaction.getPrice();
            return i;
        }, Integer::sum);


        int total = completed + waiting;
        if (total == 0) total = 1;

        model.addAttribute("completedTransaction", round((float) completed / total * 100));
        model.addAttribute("waitingTransaction", round((float) waiting / total * 100));

        //(до 1000, 1000-2500, 5000-10000, 10000-15000 и более 15000)

        int by1000 = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getPrice() < 1000) return i + 1;
            return i;
        }, Integer::sum);

        int with1000by2500 = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getPrice() >= 1000 && transaction.getPrice() < 2500) return i + 1;
            return i;
        }, Integer::sum);

        int with2500by5000 = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getPrice() >= 2500 && transaction.getPrice() < 5000) return i + 1;
            return i;
        }, Integer::sum);

        int with5000by10000 = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getPrice() >= 5000 && transaction.getPrice() < 10000) return i + 1;
            return i;
        }, Integer::sum);

        int with10000by15000 = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getPrice() >= 10000 && transaction.getPrice() < 15000) return i + 1;
            return i;
        }, Integer::sum);

        int with15000 = transactions.stream().reduce(0, (i, transaction) -> {
            if (transaction.getPrice() >= 15000) return i + 1;
            return i;
        }, Integer::sum);

        model.addAttribute("by1000", by1000);
        model.addAttribute("with1000by2500", with1000by2500);
        model.addAttribute("with2500by5000", with2500by5000);
        model.addAttribute("with5000by10000", with5000by10000);
        model.addAttribute("with10000by15000", with10000by15000);
        model.addAttribute("with15000", with15000);


        return "stats";
    }
}
