package com.peeyush.Transaction.service;

import com.peeyush.Transaction.entity.Account;
import com.peeyush.Transaction.exception.AccountNotFoundException;
import com.peeyush.Transaction.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeadLockService {
    private final AccountRepository accountRepository;

    public DeadLockService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional
    public void lockAThenB(Long aId, Long bId){
        Account a = accountRepository.findByIdForUpdate(aId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        System.out.println(Thread.currentThread().getName() + " Locked A : " + a.getBalance());

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        Account b = accountRepository.findByIdForUpdate(bId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        System.out.println(Thread.currentThread().getName() + "Locked B : " + b.getBalance());
    }

    @Transactional
    public void lockBThenA(Long aId, Long bId){
        Account a = accountRepository.findByIdForUpdate(bId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        System.out.println(Thread.currentThread().getName() + " Locked A : " + a.getBalance());

        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        Account b = accountRepository.findByIdForUpdate(bId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        System.out.println(Thread.currentThread().getName() + "Locked B : " + b.getBalance());
    }
}
