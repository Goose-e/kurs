package com.example.kurs.dto.clients.create;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

@Data
public class CreateClientResponseDTO implements Serializable {
    private String message;
    private String passportNumber;
    private Date birthDate;
    private String phone;
    private String email;
    private String address;
    private String fullName;

    public CreateClientResponseDTO() {
    }

    public CreateClientResponseDTO(String message, String passportNumber, Date birthDate, String phone, String email, String address, String fullName) {
        this.message = message;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.fullName = fullName;
    }
}
