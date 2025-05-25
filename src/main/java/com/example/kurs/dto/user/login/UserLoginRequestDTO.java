package com.example.kurs.dto.user.login;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginRequestDTO {
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;

    public UserLoginRequestDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

}
