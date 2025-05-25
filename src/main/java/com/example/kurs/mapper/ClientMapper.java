package com.example.kurs.mapper;

import com.example.kurs.dto.clients.create.CreateClientRequestDTO;
import com.example.kurs.dto.clients.create.CreateClientResponseDTO;
import com.example.kurs.dto.clients.edit.EditClientRequestDTO;
import com.example.kurs.dto.clients.edit.EditClientResponseDTO;
import com.example.kurs.models.Client;
import com.example.kurs.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientMapper {

    public Client mapClientToChangedClient(Client client, EditClientRequestDTO editClient) {
        client.setPhone(editClient.getPhone() == null ? client.getPhone() : editClient.getPhone());
        client.setEmail(editClient.getEmail() == null ? client.getEmail() : editClient.getEmail());
        client.setAddress(editClient.getAddress() == null ? client.getAddress() : editClient.getAddress());
        client.setPassportNumber(editClient.getPassportNumber() == null ? client.getPassportNumber() : editClient.getPassportNumber());
        client.setBirthDate(editClient.getBirthDate() == null ? client.getBirthDate() : editClient.getBirthDate());
        return client;
    }

    public EditClientResponseDTO mapClientToChangedClientDTO(Client client){
        EditClientResponseDTO responseDTO = new EditClientResponseDTO();
        responseDTO.setMessage("Client has been updated");
        responseDTO.setAddress(client.getAddress());
        responseDTO.setEmail(client.getEmail());
        responseDTO.setPhone(client.getPhone());
        responseDTO.setBirthDate(client.getBirthDate());
        responseDTO.setPassportNumber(client.getPassportNumber());
        return responseDTO;
    }

    public Client mapCreateClientToClient(CreateClientRequestDTO createClientRequestDTO, User userId) {
        Client client = new Client();
        client.setUser(userId);
        client.setEmail(createClientRequestDTO.getEmail());
        client.setAddress(createClientRequestDTO.getAddress());
        client.setPhone(createClientRequestDTO.getPhone());
        client.setBirthDate(createClientRequestDTO.getBirthDate());
        client.setPassportNumber(createClientRequestDTO.getPassportNumber());
        return client;
    }

    public CreateClientResponseDTO mapClientToCreatedResponseDTO(Client client, User user) {
        CreateClientResponseDTO responseDTO = new CreateClientResponseDTO();
        responseDTO.setMessage("Client created successfully");
        responseDTO.setAddress(client.getAddress());
        responseDTO.setEmail(client.getEmail());
        responseDTO.setPhone(client.getPhone());
        responseDTO.setBirthDate(client.getBirthDate());
        responseDTO.setPassportNumber(client.getPassportNumber());
        responseDTO.setFullName(user.getFullName());
        return responseDTO;
    }
}
