package com.example.kurs.dto.user.login;

import lombok.Data;

@Data
public class UserLoginResponseDTO {
    private String token;
    private String message;

    public UserLoginResponseDTO(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public UserLoginResponseDTO() {
    }
}
