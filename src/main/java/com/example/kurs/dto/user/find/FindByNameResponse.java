package com.example.kurs.dto.user.find;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class FindByNameResponse extends ResponseEntity<FindByNameResponseListDTO> {
    public FindByNameResponse(FindByNameResponseListDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
