package com.peeyush.Transaction.service;

import com.peeyush.Transaction.entity.Account;
import com.peeyush.Transaction.exception.AccountNotFoundException;
import com.peeyush.Transaction.repository.AccountRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IsolationService {
    private final AccountRepository accountRepository;
    private final EntityManager entityManager;

    public IsolationService(AccountRepository accountRepository, EntityManager entityManager){
        this.accountRepository = accountRepository;
        this.entityManager = entityManager;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readAccountTwice(Long id){
        Account read1 = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException
                        ("Account1 not found"));

        System.out.println("First read : " + read1.getBalance());

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }

        entityManager.refresh(read1);

        Account read2 = accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException
                        ("Account2 not found"));

        System.out.println("Second read : " + read2.getBalance());
    }

    @Transactional
    public void updateBalance(Long id, BigDecimal balance){
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        System.out.println("T2 updating balance.....");

//        account.setBalance(account.getBalance().add(balance));

        account.setBalance(balance);

        System.out.println("T2 new balance: " + account.getBalance());
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void findHighBalanceAccounts(BigDecimal balance){
        List<Account> read1 = accountRepository.findByBalanceGreaterThan(balance);
        System.out.println("First read : " + read1.size());

        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
//
        List<Account> read2 = accountRepository.findByBalanceGreaterThan(balance);
        System.out.println("Second read : " + read2.size());
    }

}
