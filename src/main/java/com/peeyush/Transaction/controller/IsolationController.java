package com.peeyush.Transaction.controller;

import com.peeyush.Transaction.service.IsolationService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/isolation")
public class IsolationController {
    private final IsolationService isolationService;

    public IsolationController(IsolationService isolationService){
        this.isolationService = isolationService;
    }

    @GetMapping("/read/{id}")
    public String readAccountTwice(@PathVariable Long id){

        isolationService.readAccountTwice(id);

        return "Experiment Completed";
    }

    @PostMapping("/update/{id}")
    public String updateBalance(@PathVariable Long id, @RequestParam BigDecimal balance){
        isolationService.updateBalance(id, balance);
        return "Balance updated";
    }

    @GetMapping("/phantom")
    public String findHighBalanceAccounts(@RequestParam(required = false) BigDecimal balance){
        isolationService.findHighBalanceAccounts(balance);
        return "Phantom read experiment completed";
    }
}
