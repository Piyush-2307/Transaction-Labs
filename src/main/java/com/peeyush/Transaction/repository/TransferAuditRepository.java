package com.peeyush.Transaction.repository;

import com.peeyush.Transaction.entity.TransferAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferAuditRepository extends JpaRepository<TransferAudit, Long> {
}
