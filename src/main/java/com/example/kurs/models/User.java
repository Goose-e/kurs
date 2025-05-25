package com.example.kurs.models;

import jakarta.persistence.*;
import lombok.Data;

import static com.example.kurs.services.GenerateCode.generateCode;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "role", nullable = false)
    private String role;

    @PrePersist
    public void prePersist() {
        userCode = generateCode(User.this.getClass());
    }
}
