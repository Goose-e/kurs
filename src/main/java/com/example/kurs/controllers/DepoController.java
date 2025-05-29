package com.example.kurs.controllers;

import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.services.IDeposits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/depo")
public class DepoController {
    private final IDeposits deposits;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateDepoResponseDTO> createDeposit(@RequestBody CreateDepoRequestDTO createDepoRequestDTO) {
        return deposits.createDeposit(createDepoRequestDTO);
    }
}
