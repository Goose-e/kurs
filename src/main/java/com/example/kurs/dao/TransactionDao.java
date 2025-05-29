package com.example.kurs.dao;

import com.example.kurs.models.Transaction;
import com.example.kurs.repo.TransactionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class TransactionDao {
    private final TransactionRepo transactionRepo;

    public void save(Transaction transaction) {
        transactionRepo.save(transaction);
    }
}
