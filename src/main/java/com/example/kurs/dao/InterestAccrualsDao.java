package com.example.kurs.dao;

import com.example.kurs.models.InterestAccruals;
import com.example.kurs.repo.InterestAccrualsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class InterestAccrualsDao {
    private final InterestAccrualsRepo repo;

    public void save(InterestAccruals interestAccruals) {
        repo.save(interestAccruals);
    }

    public void saveAll(List<InterestAccruals> interestAccruals) {
        repo.saveAll(interestAccruals);
    }
}
