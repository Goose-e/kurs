package com.example.kurs.dto.deposites.report;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ReportResponse extends ResponseEntity<ReportResponseListDTO> {
    public ReportResponse(ReportResponseListDTO body, HttpStatusCode status) {
        super(body, status);
    }
}
