package com.example.kurs.dto.deposites.create;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class CreateDepoRequestDTO implements Serializable {
    @NotNull
    private BigDecimal depoSum;
    @Min(1)
    @Max(3)
    @NotNull
    private int depoTypeId;

}
