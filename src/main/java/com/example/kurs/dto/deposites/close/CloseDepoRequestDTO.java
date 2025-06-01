package com.example.kurs.dto.deposites.close;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CloseDepoRequestDTO implements Serializable {
    @NotNull
    private String depoCode;

    public CloseDepoRequestDTO(String depoCode) {
        this.depoCode = depoCode;
    }

    public CloseDepoRequestDTO() {
    }
}
