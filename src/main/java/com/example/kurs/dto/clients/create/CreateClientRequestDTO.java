package com.example.kurs.dto.clients.create;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class CreateClientRequestDTO implements Serializable {
    @NotNull
    private String passportNumber;
    @NotNull
    private Date birthDate;
    private String phone;
    private String email;
    private String address;

    public CreateClientRequestDTO(String passportNumber, Date birthDate, String phone, String email, String address) {
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
