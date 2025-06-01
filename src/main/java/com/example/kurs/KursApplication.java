package com.example.kurs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KursApplication {

    public static void main(String[] args) {
        SpringApplication.run(KursApplication.class, args);
    }

}
