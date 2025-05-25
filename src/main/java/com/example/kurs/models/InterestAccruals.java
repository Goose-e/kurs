package com.example.kurs.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "interest_accruals")
@Data
public class InterestAccruals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accrual_id")
    private Long accrualId;

    @ManyToOne
    @JoinColumn(name = "deposit_id", nullable = false)
    private Deposits deposit;

    @Column(name = "accrual_date", nullable = false)
    private LocalDate accrualDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
}
