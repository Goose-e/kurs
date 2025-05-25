package com.example.kurs.dto.user.create;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


public class UserCreateResponse extends ResponseEntity<UserCreateResponseDTO> {
    private UserCreateResponseDTO response;
    private HttpStatusCode status;

    public UserCreateResponse(UserCreateResponseDTO body, HttpStatusCode status) {
        super(body,status);
    }
}
