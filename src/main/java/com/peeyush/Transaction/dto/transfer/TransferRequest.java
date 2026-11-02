package com.peeyush.Transaction.dto.transfer;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;


public record TransferRequest(

        @NotNull
        Long fromAccountId,

        @NotNull
        Long toAccountId,

        @NotNull
        BigDecimal amount
){}
