package com.example.kurs.dto.deposites.create;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CreateDepoResponse extends ResponseEntity<CreateDepoResponseDTO> {
    public CreateDepoResponse(CreateDepoResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
