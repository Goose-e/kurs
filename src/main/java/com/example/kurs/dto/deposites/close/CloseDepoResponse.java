package com.example.kurs.dto.deposites.close;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class CloseDepoResponse extends ResponseEntity<CloseDepoResponseDTO> {

    public CloseDepoResponse(CloseDepoResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
