package com.example.kurs.services;

import com.example.kurs.dto.deposites.change_type.ChangeDepoTypeRequestDTO;
import com.example.kurs.dto.deposites.change_type.ChangeDepoTypeResponseDTO;
import com.example.kurs.dto.deposites.close.CloseDepoRequestDTO;
import com.example.kurs.dto.deposites.close.CloseDepoResponseDTO;
import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.dto.deposites.get_all.GetAllDepoResponseDTO;
import com.example.kurs.dto.deposites.report.ReportRequestDTO;
import com.example.kurs.dto.deposites.top_up.TopUpDepoRequestDTO;
import org.springframework.http.ResponseEntity;

public interface IDeposits {
    //USER
    ResponseEntity<CreateDepoResponseDTO> createDeposit(CreateDepoRequestDTO depoRequestDTO);

    ResponseEntity<GetAllDepoResponseDTO> getAllDeposits();


    ResponseEntity<TopUpDepoRequestDTO> topUpDepo(TopUpDepoRequestDTO depoRequestDTO);

    //ADMIN
    ResponseEntity<ReportRequestDTO> report(ReportRequestDTO reportRequestDTO);

    ResponseEntity<CloseDepoResponseDTO> closeDepo(CloseDepoRequestDTO closeDepoRequestDTO);

    ResponseEntity<ChangeDepoTypeResponseDTO> changeDepoType(ChangeDepoTypeRequestDTO changeDepoTypeRequestDTO);
}
