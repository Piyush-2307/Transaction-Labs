package com.peeyush.Transaction.controller;

import com.peeyush.Transaction.dto.account.AccountResponse;
import com.peeyush.Transaction.dto.account.CreateAccountRequest;
import com.peeyush.Transaction.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public AccountResponse createAccount(@Valid @RequestBody CreateAccountRequest request){
        return accountService.createAccount(request);
    }

    @GetMapping("/{id}")
    public AccountResponse findAccount(@PathVariable Long id){
        return accountService.findAccount(id);
    }
}
