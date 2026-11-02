package com.peeyush.Transaction.mapper;

import com.peeyush.Transaction.dto.account.AccountResponse;
import com.peeyush.Transaction.dto.account.CreateAccountRequest;
import com.peeyush.Transaction.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(CreateAccountRequest request){
        return new Account(request.ownerName(), request.balance());
    }

    public AccountResponse toResponse(Account account){
        return new AccountResponse(account.getId(), account.getOwnerName(), account.getBalance());
    }
}
