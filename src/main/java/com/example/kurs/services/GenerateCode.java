package com.example.kurs.services;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateCode {
    public static String generateCode(Object o) {
        return o.getClass().getSimpleName() + "-" + UUID.randomUUID().toString().replaceAll("-", "") + "-" ;
    }
}