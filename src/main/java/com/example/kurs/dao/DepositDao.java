package com.example.kurs.dao;

import com.example.kurs.models.Deposit;
import com.example.kurs.repo.DepositRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DepositDao {
    private final DepositRepo depositRepo;

    public void save(Deposit deposit) {
        depositRepo.save(deposit);
    }
}
