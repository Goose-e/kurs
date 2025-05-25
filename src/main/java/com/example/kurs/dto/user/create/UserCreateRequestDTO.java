package com.example.kurs.dto.user.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCreateRequestDTO {
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String fullName;
    @NotNull
    private String role;
}
