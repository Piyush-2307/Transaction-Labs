package com.peeyush.Transaction.dto.account;

import java.math.BigDecimal;

public record AccountResponse(
        Long id,
        String ownerName,
        BigDecimal balance
){}
