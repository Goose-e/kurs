package com.example.kurs.mapper;

import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.models.Client;
import com.example.kurs.models.Deposit;
import com.example.kurs.models.InterestAccruals;
import com.example.kurs.models.Transaction;
import com.example.kurs.models.enums.DepositTypeEnum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Component
public class DepoMapper {

    public Deposit createDeposit(CreateDepoRequestDTO dto, Client client) {
        DepositTypeEnum depType = DepositTypeEnum.fromId(dto.getDepoTypeId());
        Deposit deposit = new Deposit();
        deposit.setDepositType(depType);
        deposit.setBalance(dto.getDepoSum());
        deposit.setClient(client);
        deposit.setOpenDate(LocalDateTime.now());
        deposit.setCloseDate(deposit.getOpenDate().plusMonths(depType.getTermMonths()));
        return deposit;
    }

    public CreateDepoResponseDTO createDepoResponseDTO(Deposit deposit, BigDecimal result) {
        CreateDepoResponseDTO dto = new CreateDepoResponseDTO();
        dto.setMessage("Success");
        dto.setResult(result);
        dto.setWithdraw(deposit.getDepositType().isCanWithdraw());
        dto.setAddFunds(deposit.getDepositType().isCanAddFunds());
        dto.setOpenDate(deposit.getOpenDate());
        dto.setCloseDate(deposit.getCloseDate());
        return dto;
    }

    public InterestAccruals createInterestAccural(Deposit deposit, BigDecimal amount) {
        InterestAccruals interestAccruals = new InterestAccruals();
        interestAccruals.setAmount(amount);
        interestAccruals.setDeposit(deposit);
        return interestAccruals;
    }

    public Transaction createTransaction(Deposit deposit, BigDecimal amount, String operationType) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDeposit(deposit);
        transaction.setOperationType(operationType);
        transaction.setDescription(deposit.getDepositType().getDescription());
        return transaction;
    }
}
