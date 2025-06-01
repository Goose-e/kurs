package com.example.kurs.dto.deposites.withdraw;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WithDrawRequestDTO implements Serializable {
    private String depoCode;
    private BigDecimal amount;

    public WithDrawRequestDTO() {
    }

    public WithDrawRequestDTO(String depoCode, BigDecimal amount) {
        this.depoCode = depoCode;
        this.amount = amount;
    }
}
