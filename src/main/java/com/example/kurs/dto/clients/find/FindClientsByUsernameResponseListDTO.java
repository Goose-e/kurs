package com.example.kurs.dto.clients.find;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FindClientsByUsernameResponseListDTO implements Serializable {
    private List<FindClientsByUsernameResponseDTO> clients;
    private String message;

    public FindClientsByUsernameResponseListDTO(List<FindClientsByUsernameResponseDTO> clients, String message) {
        this.clients = clients;
        this.message = message;
    }

    public FindClientsByUsernameResponseListDTO() {
    }
}
