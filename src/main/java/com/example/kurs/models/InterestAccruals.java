package com.example.kurs.models;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private Deposit deposit;

    @Column(name = "accrual_date", nullable = false)
    private LocalDateTime accrualDate;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @PrePersist
    public void prePersist() {
        accrualDate = LocalDateTime.now();
    }
}
