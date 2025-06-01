package com.example.kurs.dto.deposites.get_all_for_user;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class GetAllDepoResponse extends ResponseEntity<GetAllDepositsForUserListDTO> {


    public GetAllDepoResponse(GetAllDepositsForUserListDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
