package com.example.kurs.services;

import com.example.kurs.dto.deposites.close.CloseDepoRequestDTO;
import com.example.kurs.dto.deposites.close.CloseDepoResponseDTO;
import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.dto.deposites.get_all_for_user.GetAllDepositsForUserListDTO;
import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseListDTO;
import com.example.kurs.dto.deposites.report.ReportResponseListDTO;
import com.example.kurs.dto.deposites.top_up.TopUpDepoResponseDTO;
import com.example.kurs.dto.deposites.top_up.TopUpRequestDTO;
import com.example.kurs.dto.deposites.withdraw.WithDrawRequestDTO;
import com.example.kurs.dto.deposites.withdraw.WithDrawResponseDTO;
import org.springframework.http.ResponseEntity;

public interface IDeposits {
    //USER
    ResponseEntity<CreateDepoResponseDTO> createDeposit(CreateDepoRequestDTO depoRequestDTO);

    ResponseEntity<GetAllDepositsForUserListDTO> getAllDepositsForUser(String usesrname);


    ResponseEntity<TopUpDepoResponseDTO> topUpDepo(TopUpRequestDTO depoRequestDTO);

    ResponseEntity<GetTransHistoryForUserResponseListDTO> getTransHistoryForUser(String username);

    ResponseEntity<WithDrawResponseDTO> withDraw(WithDrawRequestDTO depositRequestDTO);

    //ADMIN

    ResponseEntity<ReportResponseListDTO> report(String period);

    ResponseEntity<CloseDepoResponseDTO> closeDepo(CloseDepoRequestDTO closeDepoRequestDTO);

    void accrueMonthlyInterest();
}
