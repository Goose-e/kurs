package com.example.kurs.services.servicesImpl;

import com.example.kurs.dto.deposites.change_type.ChangeDepoTypeRequestDTO;
import com.example.kurs.dto.deposites.change_type.ChangeDepoTypeResponseDTO;
import com.example.kurs.dto.deposites.close.CloseDepoRequestDTO;
import com.example.kurs.dto.deposites.close.CloseDepoResponseDTO;
import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.dto.deposites.get_all.GetAllDepoResponseDTO;
import com.example.kurs.dto.deposites.report.ReportRequestDTO;
import com.example.kurs.dto.deposites.top_up.TopUpDepoRequestDTO;
import com.example.kurs.services.IDeposits;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepoService implements IDeposits {
    @Override
    public ResponseEntity<CreateDepoResponseDTO> createDeposit(CreateDepoRequestDTO depoRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<GetAllDepoResponseDTO> getAllDeposits() {
        return null;
    }

    @Override
    public ResponseEntity<TopUpDepoRequestDTO> topUpDepo(TopUpDepoRequestDTO depoRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ReportRequestDTO> report(ReportRequestDTO reportRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<CloseDepoResponseDTO> closeDepo(CloseDepoRequestDTO closeDepoRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<ChangeDepoTypeResponseDTO> changeDepoType(ChangeDepoTypeRequestDTO changeDepoTypeRequestDTO) {
        return null;
    }
}
