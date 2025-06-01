package com.example.kurs.dto.deposites.close;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CloseDepoResponseDTO implements Serializable {
    private LocalDateTime closeDate;
    private BigDecimal balance;
    private String message;

    public CloseDepoResponseDTO() {
    }

    public CloseDepoResponseDTO(LocalDateTime closeDate, BigDecimal balance, String message) {
        this.closeDate = closeDate;
        this.balance = balance;
        this.message = message;
    }
}
