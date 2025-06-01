package com.example.kurs.dto.deposites.top_up;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class TopUpRequestDTO implements Serializable {
    @NotNull
    private String depoCode;
    @NotNull
    @NotEmpty
    private BigDecimal amount;

    public TopUpRequestDTO() {
    }

    public TopUpRequestDTO(String depoCode, BigDecimal amount) {
        this.depoCode = depoCode;
        this.amount = amount;
    }
}
