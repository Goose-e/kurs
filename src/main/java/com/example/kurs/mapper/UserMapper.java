package com.example.kurs.mapper;

import com.example.kurs.dto.user.create.UserCreateRequestDTO;
import com.example.kurs.dto.user.edit.EditUserRequestDTO;
import com.example.kurs.models.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User mapUserDtoToUser(UserCreateRequestDTO userCreateRequestDTO) {
        User user = new User();
        user.setRole(checkRole(userCreateRequestDTO.getRole()));
        user.setPasswordHash(passwordEncoder.encode(userCreateRequestDTO.getPassword()));
        user.setUsername(userCreateRequestDTO.getUsername());
        user.setFullName(userCreateRequestDTO.getFullName());
        return user;
    }

    public User mapEditUserDtoToUser(EditUserRequestDTO editUserRequestDTO, User user) {

        user.setFullName(editUserRequestDTO.getNewFullName() == null ? user.getFullName() : editUserRequestDTO.getNewFullName());
        user.setUsername(editUserRequestDTO.getUsername() == null ? user.getUsername() : editUserRequestDTO.getUsername());
        user.setPasswordHash(editUserRequestDTO.getNewPassword() == null ? user.getPasswordHash() : passwordEncoder.encode(editUserRequestDTO.getNewPassword()));
        user.setRole(editUserRequestDTO.getNewRole() == null ? user.getRole() : checkRole(editUserRequestDTO.getNewRole()));
        return user;
    }

    public String checkRole(String role) {
        role = role.toUpperCase();
        if (role.equals("ADMIN") || role.equals("USER")) {
            return role;
        } else {
            return "USER";
        }
    }
}
