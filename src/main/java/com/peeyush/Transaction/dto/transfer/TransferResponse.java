package com.peeyush.Transaction.dto.transfer;

import java.math.BigDecimal;

public record TransferResponse(

        String message,
        Long fromAccountId,
        Long toAccountId,
        BigDecimal amount
) {
}
