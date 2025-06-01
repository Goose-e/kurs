package com.example.kurs.dto.deposites.withdraw;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


public class WithDrawResponse extends ResponseEntity<WithDrawResponseDTO> {

    public WithDrawResponse(WithDrawResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
