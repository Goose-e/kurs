package com.example.kurs.dto.user.edit;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class EditUserResponse extends ResponseEntity<EditUserResponseDTO> {
    public EditUserResponse(EditUserResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
