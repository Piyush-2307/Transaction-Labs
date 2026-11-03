package com.peeyush.Transaction.service;

import com.peeyush.Transaction.entity.TransferAudit;
import com.peeyush.Transaction.repository.TransferAuditRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransferAuditService {
    private final TransferAuditRepository auditRepository;

    public TransferAuditService(TransferAuditRepository auditRepository){
        this.auditRepository = auditRepository;
    }


    @Transactional(propagation = Propagation.REQUIRED)
    public void record(){
        auditRepository.save(new TransferAudit("Transfer attempted"));
    }
}
