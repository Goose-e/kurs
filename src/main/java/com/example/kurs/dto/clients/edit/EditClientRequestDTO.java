package com.example.kurs.dto.clients.edit;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class EditClientRequestDTO implements Serializable {
    @NotNull
    private String PassportNumber;

    public EditClientRequestDTO(String passportNumber, Date birthDate, String phone, String email, String address) {
        PassportNumber = passportNumber;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    @NotNull
    private Date birthDate;
    private String phone;
    private String email;
    private String address;
}
