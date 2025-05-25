package com.example.kurs.repo;

import com.example.kurs.dto.user.find.FindByNameResponseDTO;
import com.example.kurs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    @Query("SELECT new com.example.kurs.dto.user.find.FindByNameResponseDTO(u.username,u.fullName,u.role) FROM User u WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :username, '%'))")
    List<FindByNameResponseDTO> findUsersByName(@Param("username") String username);
}
