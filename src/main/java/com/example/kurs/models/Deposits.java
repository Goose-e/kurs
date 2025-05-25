package com.example.kurs.models;

import com.example.kurs.models.enums.DepositTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "deposits")
@Data
public class Deposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deposit_id")
    private Long depositId;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "deposit_type", nullable = false)
    private DepositTypeEnum depositType;

    @Column(name = "open_date", nullable = false)
    private LocalDate openDate;

    @Column(name = "close_date")
    private LocalDate closeDate;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "last_accrual")
    private LocalDate lastAccrual;
}
