package com.example.kurs.dto.user.edit;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EditUserRequestDTO implements Serializable {
    @NotEmpty
    private String username;
    private String newPassword;
    private String newFullName;
    private String newUsername;
    private String newRole;

    public EditUserRequestDTO(String username, String newPassword, String newFullName, String newUsername, String newRole) {
        this.username = username;
        this.newPassword = newPassword;
        this.newFullName = newFullName;
        this.newUsername = newUsername;
        this.newRole = newRole;
    }
}
