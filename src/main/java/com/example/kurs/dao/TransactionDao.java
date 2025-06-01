package com.example.kurs.dao;

import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseDTO;
import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseListDTO;
import com.example.kurs.models.Transaction;
import com.example.kurs.repo.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TransactionDao {
    private final TransactionRepo transactionRepo;

    public void save(Transaction transaction) {
        transactionRepo.save(transaction);
    }
    public List<GetTransHistoryForUserResponseDTO> getTransHistoryForUser(Long clientId) {
        return transactionRepo.getTransHistoryByUserCode(clientId);
    }
}
