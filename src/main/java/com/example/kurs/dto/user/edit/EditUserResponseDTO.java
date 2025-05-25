package com.example.kurs.dto.user.edit;

import lombok.Data;

import java.io.Serializable;

@Data
public class EditUserResponseDTO implements Serializable {
    String message;

    public EditUserResponseDTO(String message, String newUsername, String newPassword, String newFullName, String newRole) {
        this.message = message;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
        this.newFullName = newFullName;
        this.newRole = newRole;
    }

    String newUsername;
    String newPassword;
    String newFullName;
    String newRole;


    public EditUserResponseDTO() {
    }
}
