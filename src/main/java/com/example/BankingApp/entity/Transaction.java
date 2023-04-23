package com.example.BankingApp.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long Id;

    @Column
    String currency;

    @Column
    String amount;

    @Column
    String accountId;
}
