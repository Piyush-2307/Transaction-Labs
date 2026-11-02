package com.peeyush.Transaction.dto.account;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CreateAccountRequest(

        @NotBlank
        String ownerName,

        @NotNull
        BigDecimal balance
){}
