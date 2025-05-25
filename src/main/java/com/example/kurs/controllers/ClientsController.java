package com.example.kurs.controllers;

import com.example.kurs.dto.clients.create.CreateClientRequestDTO;
import com.example.kurs.dto.clients.create.CreateClientResponseDTO;
import com.example.kurs.dto.clients.edit.EditClientRequestDTO;
import com.example.kurs.dto.clients.edit.EditClientResponseDTO;
import com.example.kurs.dto.clients.find.FindClientsByUsernameRequestDTO;
import com.example.kurs.dto.clients.find.FindClientsByUsernameResponseListDTO;
import com.example.kurs.services.IClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/clients")
public class ClientsController {
    private final IClientService clientService;

    @PostMapping(value = "/create")
    public ResponseEntity<CreateClientResponseDTO> createClient(@RequestBody CreateClientRequestDTO createClientRequestDTO) {
        return clientService.createClient(createClientRequestDTO);
    }

    @PostMapping(value = "/find")
    public ResponseEntity<FindClientsByUsernameResponseListDTO> findClientsByUsername(@RequestBody FindClientsByUsernameRequestDTO findClientsByUsernameRequestDTO) {
        return clientService.findClientsByUsername(findClientsByUsernameRequestDTO);
    }

    @PostMapping(value = "/edit")
    public ResponseEntity<EditClientResponseDTO> editClient(@RequestBody EditClientRequestDTO editClientRequestDTO) {
        return clientService.editClients(editClientRequestDTO);
    }
}
