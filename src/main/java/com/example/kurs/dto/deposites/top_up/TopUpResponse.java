package com.example.kurs.dto.deposites.top_up;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


public class TopUpResponse extends ResponseEntity<TopUpDepoResponseDTO> {
    public TopUpResponse(TopUpDepoResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
