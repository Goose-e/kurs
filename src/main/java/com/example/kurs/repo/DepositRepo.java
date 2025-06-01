package com.example.kurs.repo;

import com.example.kurs.dto.deposites.get_all_for_user.GetAllDepositsForUserListDTO;
import com.example.kurs.dto.deposites.get_all_for_user.GetAllUsersDepoResponseDTO;
import com.example.kurs.models.Deposit;
import com.example.kurs.models.DepositTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepositRepo extends JpaRepository<Deposit, Long> {

    @Query("SELECT new com.example.kurs.dto.deposites.get_all_for_user.GetAllUsersDepoResponseDTO(" +
            "u.username,d.depoCode, d.openDate, d.closeDate, dt.name, d.balance) " +
            "FROM Deposit d " +
            "JOIN d.client c " +
            "JOIN c.user u " +
            "JOIN d.type dt " +
            "WHERE u.userCode = :userCode")
    List<GetAllUsersDepoResponseDTO> getAllDepositByUserCode(@Param("userCode") String userCode);

    Deposit getDepositByDepoCode(String depoCode);


}
