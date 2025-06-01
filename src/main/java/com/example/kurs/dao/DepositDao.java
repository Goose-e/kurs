package com.example.kurs.dao;

import com.example.kurs.dto.deposites.get_all_for_user.GetAllDepositsForUserListDTO;
import com.example.kurs.dto.deposites.get_all_for_user.GetAllUsersDepoResponseDTO;
import com.example.kurs.models.Deposit;
import com.example.kurs.models.DepositTypes;
import com.example.kurs.repo.DepositRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
