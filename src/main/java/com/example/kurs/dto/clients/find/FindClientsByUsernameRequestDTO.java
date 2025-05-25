package com.example.kurs.dto.clients.find;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FindClientsByUsernameRequestDTO implements Serializable {
    @NotEmpty
    private String usernameOrFullName;

}
