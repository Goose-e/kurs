package com.example.kurs.dto.deposites.top_up;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TopUpDepoResponseDTO implements Serializable {

    private BigDecimal newBalance;
    private String message;


    public TopUpDepoResponseDTO() {
    }

    public TopUpDepoResponseDTO(BigDecimal newBalance, String message) {
        this.newBalance = newBalance;
        this.message = message;
    }
}
