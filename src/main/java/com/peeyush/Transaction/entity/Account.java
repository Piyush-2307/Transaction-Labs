package com.peeyush.Transaction.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "balance")
    private BigDecimal balance;

    public Account(String ownerName, BigDecimal balance){
        this.ownerName = ownerName;
        this.balance = balance;
    }
}
