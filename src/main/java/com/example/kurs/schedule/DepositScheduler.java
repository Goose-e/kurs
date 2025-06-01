package com.example.kurs.schedule;

import com.example.kurs.services.IDeposits;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DepositScheduler {

    private final IDeposits depositService;


    @Scheduled(cron = "0 0 0 1 * ?")
    public void scheduleMonthlyAccrual() {
        depositService.accrueMonthlyInterest();
    }
}
