package com.peeyush.Transaction.service;

import com.peeyush.Transaction.dto.transfer.TransferRequest;
import com.peeyush.Transaction.dto.transfer.TransferResponse;
import com.peeyush.Transaction.entity.Account;
import com.peeyush.Transaction.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferService {
    private final AccountRepository accountRepository;

    public TransferService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Transactional
    public TransferResponse transfer(TransferRequest request){
        Account fromAccount = accountRepository.findById(request.fromAccountId())
                .orElseThrow(() -> new RuntimeException("Source account not found"));

        Account toAccount = accountRepository.findById(request.toAccountId())
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        if(fromAccount.getBalance().compareTo(request.amount()) < 0){
            throw new RuntimeException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(request.amount()));
        toAccount.setBalance(toAccount.getBalance().add(request.amount()));

        return new TransferResponse(
                "Transfer completed successfully",
                fromAccount.getId(),
                toAccount.getId(),
                request.amount()
        );
    }
}
