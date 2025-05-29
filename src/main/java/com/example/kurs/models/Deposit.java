package com.example.kurs.models;

import com.example.kurs.models.enums.DepositTypeEnum;
import com.example.kurs.models.enums.DepositTypeEnumConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "type_id", nullable = false)
    @Convert(converter = DepositTypeEnumConverter.class)
    private DepositTypeEnum depositType;

    @Column(name = "open_date", nullable = false)
    private LocalDateTime openDate;

    @Column(name = "close_date")
    private LocalDateTime closeDate;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "last_accrual")
    private LocalDateTime lastAccrual;
    @Column(name = "type")
    private String type;

    @PrePersist
    public void prePersist() {
        lastAccrual = LocalDateTime.now();
        type = "Later";
    }
}
