package com.example.kurs.dto.clients.find;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class FindClientsByUsernameResponse  extends ResponseEntity<FindClientsByUsernameResponseListDTO> {
    public FindClientsByUsernameResponse(FindClientsByUsernameResponseListDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
