package com.example.kurs.repo;

import com.example.kurs.dto.clients.find.FindClientsByUsernameResponseDTO;
import com.example.kurs.models.Client;
import com.example.kurs.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {

    @Query("SELECT c FROM Client c JOIN c.user u WHERE u.userCode =:userCode")
    Client findClientByCode(@Param("userCode") String userCode);

    @Query("SELECT u FROM User u WHERE u.userCode =:userCode")
    User findUserIdByUserCode(@Param("userCode") String userCode);

    @Query("SELECT new com.example.kurs.dto.clients.find.FindClientsByUsernameResponseDTO(" +
            "        u.fullName," +
            "        c.passportNumber," +
            "        c.birthDate," +
            "        c.phone," +
            "        c.email," +
            "        c.address" +
            "    )" +
            "    FROM Client c" +
            "   JOIN c.user u" +
            "    WHERE LOWER(u  .username) LIKE LOWER(CONCAT('%', :username, '%'))" +
            "       OR LOWER(u.fullName) LIKE LOWER(CONCAT('%', :fullName, '%'))")
    List<FindClientsByUsernameResponseDTO> findClientsByUsernameOrFullName(@Param("username") String username, @Param("fullName") String fullName);
}
