package com.example.kurs.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.example.kurs.services.GenerateCode.generateCode;

@Entity
@Table(name = "deposits")
@Data
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_id")
    private Long depositId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private DepositTypes type;

    @Column(name = "open_date", nullable = false)
    private LocalDateTime openDate;

    @Column(name = "close_date")
    private LocalDateTime closeDate;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "last_accrual")
    private LocalDateTime lastAccrual;
    @Column(name = "depo_code")
    private String depoCode;

    @PrePersist
    public void prePersist() {
        lastAccrual = LocalDateTime.now();
        depoCode = generateCode(Deposit.this.getClass());
    }
}
