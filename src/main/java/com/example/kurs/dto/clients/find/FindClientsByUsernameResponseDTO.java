package com.example.kurs.dto.clients.find;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class FindClientsByUsernameResponseDTO implements Serializable {
    private String fullName;
    private String passportNumber;
    private Date birthDate;
    private String phone;
    private String email;
    private String address;

    public FindClientsByUsernameResponseDTO() {
    }

    public FindClientsByUsernameResponseDTO(String fullName, String passportNumber, Date birthDate, String phone, String email, String address) {
        this.fullName = fullName;
        this.passportNumber = passportNumber;
        this.birthDate = birthDate;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
}
