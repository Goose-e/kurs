package com.example.kurs.dto.clients.edit;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class EditClientResponse extends ResponseEntity<EditClientResponseDTO> {

    public EditClientResponse(EditClientResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
