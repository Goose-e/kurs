package com.example.kurs.dto.clients.create;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CreateClientResponse extends ResponseEntity<CreateClientResponseDTO> {
    public CreateClientResponse(CreateClientResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
