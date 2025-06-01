package com.example.kurs.controllers;

import com.example.kurs.dto.deposites.close.CloseDepoRequestDTO;
import com.example.kurs.dto.deposites.close.CloseDepoResponseDTO;
import com.example.kurs.services.IDeposits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/admin/depo")
public class DepoAdmController {
    private final IDeposits deposits;

    @PostMapping(value = "/close_deposit")
    public ResponseEntity<CloseDepoResponseDTO> closeDeposit(@RequestBody CloseDepoRequestDTO closeDepoRequestDTO) {
        return deposits.closeDepo(closeDepoRequestDTO);
    }

}
