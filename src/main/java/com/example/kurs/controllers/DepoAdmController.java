package com.example.kurs.controllers;

import com.example.kurs.services.IDeposits;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/admin/depo")
public class DepoAdmController {
    private final IDeposits deposits;
}
