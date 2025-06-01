package com.example.kurs.repo;

import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseDTO;
import com.example.kurs.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("SELECT new com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseDTO(d.depoCode,tr.operationDate,tr.operationType,tr.amount ) " +
            "FROM Transaction tr JOIN Deposit d JOIN Client cl  where cl.clientId =:clientId")
    List<GetTransHistoryForUserResponseDTO> getTransHistoryByUserCode(@Param("clientId") Long clientId);
}
