package com.example.kurs.services;

import com.example.kurs.dto.clients.create.CreateClientRequestDTO;
import com.example.kurs.dto.clients.create.CreateClientResponseDTO;
import com.example.kurs.dto.clients.edit.EditClientRequestDTO;
import com.example.kurs.dto.clients.edit.EditClientResponseDTO;
import com.example.kurs.dto.clients.find.FindClientsByUsernameRequestDTO;
import com.example.kurs.dto.clients.find.FindClientsByUsernameResponseListDTO;
import org.springframework.http.ResponseEntity;

public interface IClientService {
    ResponseEntity<CreateClientResponseDTO> createClient(CreateClientRequestDTO createClientRequestDTO);
    ResponseEntity<FindClientsByUsernameResponseListDTO> findClientsByUsername(FindClientsByUsernameRequestDTO findClientsByUsernameRequestDTO);

    ResponseEntity<EditClientResponseDTO> editClients(EditClientRequestDTO editClientRequestDTO);
}
