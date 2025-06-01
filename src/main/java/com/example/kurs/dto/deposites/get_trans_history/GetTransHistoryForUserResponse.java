package com.example.kurs.dto.deposites.get_trans_history;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class GetTransHistoryForUserResponse extends ResponseEntity<GetTransHistoryForUserResponseListDTO> {

    public GetTransHistoryForUserResponse(GetTransHistoryForUserResponseListDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
