package com.example.kurs.dao;

import com.example.kurs.dto.deposites.get_all_for_user.GetAllUsersDepoResponseDTO;
import com.example.kurs.dto.deposites.report.ReportResponseDTO;
import com.example.kurs.models.Deposit;
import com.example.kurs.repo.DepositRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class DepositDao {
    private final DepositRepo depositRepo;

    public void save(Deposit deposit) {
        depositRepo.save(deposit);
    }

    public List<GetAllUsersDepoResponseDTO> findAllForUserByUserCode(String userCode) {
        return depositRepo.getAllDepositByUserCode(userCode);
    }

    public Deposit getDepositByDepositCode(String depositCode) {
        return depositRepo.getDepositByDepoCode(depositCode);
    }

    public List<ReportResponseDTO> report(LocalDateTime start, LocalDateTime end) {
        return depositRepo.getAdminDepositReport(start, end);
    }

    public List<Deposit> getAllActiveDeposit() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastMonth = now.minusMonths(1);
        return depositRepo.findActiveDepositsForAccrual(now, lastMonth);
    }

    public void saveAll(List<Deposit> deposits) {
        depositRepo.saveAll(deposits);
    }
}
