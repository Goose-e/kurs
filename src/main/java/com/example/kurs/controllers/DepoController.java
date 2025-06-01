package com.example.kurs.controllers;

import com.example.kurs.dto.deposites.create.CreateDepoRequestDTO;
import com.example.kurs.dto.deposites.create.CreateDepoResponseDTO;
import com.example.kurs.dto.deposites.get_all_for_user.GetAllDepositsForUserListDTO;
import com.example.kurs.dto.deposites.get_trans_history.GetTransHistoryForUserResponseListDTO;
import com.example.kurs.dto.deposites.top_up.TopUpDepoResponseDTO;
import com.example.kurs.dto.deposites.top_up.TopUpRequestDTO;
import com.example.kurs.dto.deposites.withdraw.WithDrawRequestDTO;
import com.example.kurs.dto.deposites.withdraw.WithDrawResponseDTO;
import com.example.kurs.services.IDeposits;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/depo")
public class DepoController {
    private final IDeposits deposits;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateDepoResponseDTO> createDeposit(@RequestBody CreateDepoRequestDTO createDepoRequestDTO) {
        return deposits.createDeposit(createDepoRequestDTO);
    }

    @GetMapping(value = "/get_all_for_user")
    public ResponseEntity<GetAllDepositsForUserListDTO> getAllForUser(@RequestParam(value = "username", required = false) String username) {
        return deposits.getAllDepositsForUser(username);
    }

    @PostMapping(value = "/top_up_deposit")
    public ResponseEntity<TopUpDepoResponseDTO> topUpDeposit(@RequestBody TopUpRequestDTO topUpDepoRequestDTO) {
        return deposits.topUpDepo(topUpDepoRequestDTO);
    }


    @GetMapping(value = "/get_trans_history_for_user")
    public ResponseEntity<GetTransHistoryForUserResponseListDTO> getAllTransHistoryForUser(@RequestParam(value = "username", required = false) String username) {
        return deposits.getTransHistoryForUser(username);
    }

    @PostMapping(value = "/withdraw")
    public ResponseEntity<WithDrawResponseDTO> withdraw(@RequestBody WithDrawRequestDTO withDrawRequestDTO) {
        return deposits.withDraw(withDrawRequestDTO);
    }
}
