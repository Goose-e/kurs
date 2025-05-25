package com.example.kurs.dto.user.find;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FindByNameRequestDTO {
    @NotNull
    String username;

    public FindByNameRequestDTO(String username) {
        this.username = username;
    }
}
