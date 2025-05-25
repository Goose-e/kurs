package com.example.kurs.dto.user.find;

import lombok.Data;

import java.io.Serializable;

@Data
public class FindByNameResponseDTO implements Serializable {
    String username;
    String fullName;
    String role;

    public FindByNameResponseDTO(String username, String fullName, String role) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
    }

    public FindByNameResponseDTO() {
    }
}
