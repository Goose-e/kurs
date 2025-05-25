package com.example.kurs.services;

import com.example.kurs.dto.user.create.UserCreateRequestDTO;
import com.example.kurs.dto.user.create.UserCreateResponseDTO;
import com.example.kurs.dto.user.edit.EditUserRequestDTO;
import com.example.kurs.dto.user.edit.EditUserResponseDTO;
import com.example.kurs.dto.user.find.FindByNameRequestDTO;
import com.example.kurs.dto.user.find.FindByNameResponseListDTO;
import com.example.kurs.dto.user.login.UserLoginRequestDTO;
import com.example.kurs.dto.user.login.UserLoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity<UserCreateResponseDTO> register(UserCreateRequestDTO userCreateRequestDTO);

    ResponseEntity<UserLoginResponseDTO> login(UserLoginRequestDTO userLoginRequestDTO);

    ResponseEntity<EditUserResponseDTO> edit(EditUserRequestDTO editUserRequestDTO);

    ResponseEntity<FindByNameResponseListDTO> findByName(FindByNameRequestDTO findByNameRequestDTO);
}
