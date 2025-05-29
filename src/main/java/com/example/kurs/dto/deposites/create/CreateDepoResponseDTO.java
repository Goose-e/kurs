package com.example.kurs.dto.deposites.create;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CreateDepoResponseDTO implements Serializable {
    private String message;
    private BigDecimal result;
    private boolean withdraw;
    public boolean addFunds;
    public LocalDateTime closeDate;
    public LocalDateTime openDate;
}
