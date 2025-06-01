package com.example.kurs.dto.deposites.withdraw;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WithDrawResponseDTO implements Serializable {
    private String message;
    private BigDecimal receivedAmount;
    private BigDecimal amountRemainder;

    public WithDrawResponseDTO() {
    }

    public WithDrawResponseDTO(String message, BigDecimal receivedAmount, BigDecimal amountRemainder) {
        this.message = message;
        this.receivedAmount = receivedAmount;
        this.amountRemainder = amountRemainder;
    }
}
