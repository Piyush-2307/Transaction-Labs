package com.peeyush.Transaction.service;

import com.peeyush.Transaction.entity.Account;
import com.peeyush.Transaction.exception.AccountNotFoundException;
import com.peeyush.Transaction.exception.InsufficientBalanceException;
import com.peeyush.Transaction.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class ConcurrencyService {
    private final AccountRepository accountRepository;

    public ConcurrencyService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void withdraw(Long accountId, BigDecimal amount){
        Account account = accountRepository.findByIdForUpdate(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        System.out.println(Thread.currentThread().getName() + " acquired lock. Balance : " + account.getBalance());

        if (account.getBalance().compareTo(amount) < 0){
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        account.setBalance(account.getBalance().subtract(amount));

        System.out.println(Thread.currentThread().getName() + " new balance : " + account.getBalance());
        System.out.println("Final account balance : " + account.getBalance());

    }
}
