package com.example.kurs.repo;

import com.example.kurs.models.InterestAccruals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestAccrualsRepo extends JpaRepository<InterestAccruals, Long> {
}
