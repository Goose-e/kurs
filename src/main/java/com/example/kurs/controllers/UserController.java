package com.example.kurs.controllers;

import com.example.kurs.dto.user.create.UserCreateRequestDTO;
import com.example.kurs.dto.user.create.UserCreateResponseDTO;
import com.example.kurs.dto.user.edit.EditUserRequestDTO;
import com.example.kurs.dto.user.edit.EditUserResponseDTO;
import com.example.kurs.dto.user.find.FindByNameRequestDTO;
import com.example.kurs.dto.user.find.FindByNameResponseListDTO;
import com.example.kurs.dto.user.login.UserLoginRequestDTO;
import com.example.kurs.dto.user.login.UserLoginResponseDTO;
import com.example.kurs.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final IUserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<UserCreateResponseDTO> createUser(@RequestBody UserCreateRequestDTO userCreateRequestDTO) {
        return userService.register(userCreateRequestDTO);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserLoginResponseDTO> login(@RequestBody UserLoginRequestDTO userLoginRequestDTO) {
        return userService.login(userLoginRequestDTO);
    }

    @PostMapping(value = "/find")
    public ResponseEntity<FindByNameResponseListDTO> findByName(@RequestBody FindByNameRequestDTO findByNameRequestDTO) {
        return userService.findByName(findByNameRequestDTO);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<EditUserResponseDTO> editUser(@RequestBody EditUserRequestDTO editUserRequestDTO) {
        return userService.edit(editUserRequestDTO);
    }
}
