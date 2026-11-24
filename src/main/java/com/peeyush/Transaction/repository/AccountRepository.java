package com.peeyush.Transaction.repository;

import com.peeyush.Transaction.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByBalanceGreaterThan(BigDecimal balance);
}
