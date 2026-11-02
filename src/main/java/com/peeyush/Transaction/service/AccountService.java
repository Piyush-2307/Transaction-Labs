package com.peeyush.Transaction.service;

import com.peeyush.Transaction.dto.account.AccountResponse;
import com.peeyush.Transaction.dto.account.CreateAccountRequest;
import com.peeyush.Transaction.entity.Account;
import com.peeyush.Transaction.mapper.AccountMapper;
import com.peeyush.Transaction.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper){
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public AccountResponse createAccount(CreateAccountRequest request){
        Account account = accountMapper.toEntity(request);
        return accountMapper.toResponse(accountRepository.save(account));
    }

    public AccountResponse findAccount(Long id){
        return accountMapper.toResponse(accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("There is no account")));
    }
}
