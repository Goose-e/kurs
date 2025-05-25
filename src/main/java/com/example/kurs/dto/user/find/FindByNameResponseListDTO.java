package com.example.kurs.dto.user.find;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FindByNameResponseListDTO implements Serializable {
    private List<FindByNameResponseDTO> users;
    private String message;

    public FindByNameResponseListDTO(List<FindByNameResponseDTO> users, String message) {
        this.message = message;
        this.users = users;
    }

    public FindByNameResponseListDTO() {
    }
}
