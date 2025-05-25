package com.example.kurs.dto.user.login;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class UserLoginResponse extends ResponseEntity<UserLoginResponseDTO> {
    public UserLoginResponse(UserLoginResponseDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
