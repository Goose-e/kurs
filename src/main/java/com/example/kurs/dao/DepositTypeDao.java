package com.example.kurs.dao;

import com.example.kurs.models.DepositTypes;
import com.example.kurs.repo.DepositTypesRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DepositTypeDao {
    private final DepositTypesRepo repo;

    public DepositTypes findTypeById(Long id) {
        return repo.findDepositTypesByTypeId(id);
    }
}
