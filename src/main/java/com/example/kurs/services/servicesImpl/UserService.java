package com.example.kurs.services.servicesImpl;

import com.example.kurs.config.jwt.JwtProvider;
import com.example.kurs.dao.UserDao;
import com.example.kurs.dto.user.create.UserCreateRequestDTO;
import com.example.kurs.dto.user.create.UserCreateResponse;
import com.example.kurs.dto.user.create.UserCreateResponseDTO;
import com.example.kurs.dto.user.edit.EditUserRequestDTO;
import com.example.kurs.dto.user.edit.EditUserResponse;
import com.example.kurs.dto.user.edit.EditUserResponseDTO;
import com.example.kurs.dto.user.find.FindByNameRequestDTO;
import com.example.kurs.dto.user.find.FindByNameResponse;
import com.example.kurs.dto.user.find.FindByNameResponseDTO;
import com.example.kurs.dto.user.find.FindByNameResponseListDTO;
import com.example.kurs.dto.user.login.UserLoginRequestDTO;
import com.example.kurs.dto.user.login.UserLoginResponse;
import com.example.kurs.dto.user.login.UserLoginResponseDTO;
import com.example.kurs.mapper.UserMapper;
import com.example.kurs.models.User;
import com.example.kurs.services.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private final UserDao userDao;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<UserCreateResponseDTO> register(UserCreateRequestDTO userCreateRequestDTO) {
        User user = userDao.findByUserName(userCreateRequestDTO.getUsername());
        UserCreateResponseDTO userCreateResponseDTO = new UserCreateResponseDTO();
        HttpStatusCode code;
        if (userCreateRequestDTO.getRole().equals("USER") || userCreateRequestDTO.getRole().equals("ADMIN")) {
            if (user == null) {
                user = userMapper.mapUserDtoToUser(userCreateRequestDTO);
                if (user != null) {
                    userDao.save(user);
                    userCreateResponseDTO.setMessage("User successfully registered!");
                    code = HttpStatus.CREATED;
                } else {
                    userCreateResponseDTO.setMessage("User could not be registered!");
                    code = HttpStatus.INTERNAL_SERVER_ERROR;
                }

            } else {
                if (user.getUsername().equals(userCreateRequestDTO.getUsername())) {
                    userCreateResponseDTO.setMessage("Username is already taken");
                } else {
                    userCreateResponseDTO.setMessage("Email is already taken");
                }
                code = HttpStatus.CONFLICT;
            }
        } else {
            code = HttpStatus.UNAUTHORIZED;
            userCreateResponseDTO.setMessage("Role does not exist");
        }
        return new UserCreateResponse(userCreateResponseDTO, code);
    }

    @Override
    public ResponseEntity<UserLoginResponseDTO> login(UserLoginRequestDTO userLoginRequestDTO) {
        User user = userDao.findByUserName(userLoginRequestDTO.getLogin());
        UserLoginResponseDTO userLoginResponseDTO = new UserLoginResponseDTO();
        HttpStatusCode code;
        if (user != null) {
            try {
                Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getLogin(), userLoginRequestDTO.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(auth);
                String jwt = jwtProvider.generateJwtToken(user.getUsername(), user.getRole(), user.getUserCode());
                userLoginResponseDTO.setToken(jwt);
                userLoginResponseDTO.setMessage("User successfully signed in!");
                code = HttpStatus.OK;
            } catch (Exception e) {
                code = HttpStatus.UNAUTHORIZED;
                userLoginResponseDTO.setMessage("Invalid username or password");
            }
        } else {
            userLoginResponseDTO.setMessage("User could not be signed in!");
            code = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new UserLoginResponse(userLoginResponseDTO, code);
    }

    @Override
    public ResponseEntity<EditUserResponseDTO> edit(EditUserRequestDTO editUserRequestDTO) {
        User user = userDao.findByUserName(editUserRequestDTO.getUsername());
        HttpStatusCode code;
        EditUserResponseDTO editUserResponseDTO = new EditUserResponseDTO();
        if (user != null) {
            user = userMapper.mapEditUserDtoToUser(editUserRequestDTO, user);
            userDao.save(user);
            editUserResponseDTO.setMessage("User changed successfully");
            editUserResponseDTO.setNewFullName(user.getFullName());
            editUserResponseDTO.setNewPassword(editUserRequestDTO.getNewPassword());
            editUserResponseDTO.setNewUsername(user.getUsername());
            editUserResponseDTO.setNewRole(user.getRole());
            code = HttpStatus.OK;
        } else {
            editUserResponseDTO.setMessage("User could not be changed!");
            code = HttpStatus.NOT_FOUND;
        }
        return new EditUserResponse(editUserResponseDTO, code);
    }

    @Override
    public ResponseEntity<FindByNameResponseListDTO> findByName(FindByNameRequestDTO findByNameRequestDTO) {
        List<FindByNameResponseDTO> users = userDao.findUsers(findByNameRequestDTO.getUsername());
        HttpStatusCode code;
        FindByNameResponseListDTO findByNameResponseListDTO = new FindByNameResponseListDTO();
        if (!users.isEmpty()) {
            findByNameResponseListDTO.setMessage("Users found");
            findByNameResponseListDTO.setUsers(users);
            code = HttpStatus.OK;
        } else {
            code = HttpStatus.NOT_FOUND;
            findByNameResponseListDTO.setMessage("User not found");
        }
        return new FindByNameResponse(findByNameResponseListDTO, code);
    }
}
