package com.peeyush.Transaction.controller;

import com.peeyush.Transaction.service.ConcurrencyService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/concurrency")
public class ConcurrencyController {
    private final ConcurrencyService concurrencyService;

    public ConcurrencyController(ConcurrencyService concurrencyService){
        this.concurrencyService = concurrencyService;
    }

    @PostMapping("/withdraw/{accountId}")
    public String withdraw(@PathVariable Long accountId, @RequestParam BigDecimal amount){
        concurrencyService.withdraw(accountId, amount);
        return "Withdrawal Completed";
    }
}