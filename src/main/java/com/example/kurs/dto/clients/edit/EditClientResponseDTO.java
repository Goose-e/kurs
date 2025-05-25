package com.example.kurs.dto.clients.edit;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class EditClientResponseDTO implements Serializable {

    private String passportNumber;
    private Date birthDate;
    private String phone;
    private String email;
    private String address;
    private String message;

    public EditClientResponseDTO() {
    }

    public EditClientResponseDTO(String message, String passportNumber, Date birthDate, String phone, String email, String address) {
        this.message = message;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
