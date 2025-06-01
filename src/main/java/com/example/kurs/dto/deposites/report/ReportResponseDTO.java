package com.example.kurs.dto.deposites.report;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ReportResponseDTO implements Serializable {
    private String depoCode;
    private String clientPassport;
    private String depositType;
    private LocalDateTime openDate;
    private LocalDateTime closeDate;
    private BigDecimal balance;
    private BigDecimal totalAccrued;
    private BigDecimal totalTransactions;
}
