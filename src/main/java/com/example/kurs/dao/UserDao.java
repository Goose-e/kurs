package com.example.kurs.dao;

import com.example.kurs.dto.user.find.FindByNameResponseDTO;
import com.example.kurs.models.User;
import com.example.kurs.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserDao {
    private final UserRepo userRepo;

    public User findByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }
    public void save(User user) {
        userRepo.save(user);
    }
    public List<FindByNameResponseDTO> findUsers(String userName) {
        return userRepo.findUsersByName(userName);
    }
}
