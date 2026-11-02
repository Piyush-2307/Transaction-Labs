package com.peeyush.Transaction.controller;

import com.peeyush.Transaction.dto.transfer.TransferRequest;
import com.peeyush.Transaction.dto.transfer.TransferResponse;
import com.peeyush.Transaction.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransactionController {
    private final TransferService transferService;

    public TransactionController(TransferService transferService){
        this.transferService = transferService;
    }

    @PostMapping
    public TransferResponse transfer(@Valid @RequestBody TransferRequest request){
        return transferService.transfer(request);
    }
}
