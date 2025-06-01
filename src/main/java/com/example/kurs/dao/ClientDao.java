package com.example.kurs.dao;

import com.example.kurs.dto.clients.find.FindClientsByUsernameResponseDTO;
import com.example.kurs.models.Client;
import com.example.kurs.models.User;
import com.example.kurs.repo.ClientRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ClientDao {
    private final ClientRepo clientRepo;

    public Client findClientByCode(String username) {
        return clientRepo.findClientByCode(username);
    }

    public User findUserIdByUserCode(String username) {
        return clientRepo.findUserIdByUserCode(username);
    }

    public void saveClient(Client client) {
        clientRepo.save(client);
    }

    public Client findClientsByUserName(String username) {
        return clientRepo.findClientByUserName(username);
    }

    public List<FindClientsByUsernameResponseDTO> findClientsByUsernameOrFullName(String username) {
        return clientRepo.findClientsByUsernameOrFullName(username, username);
    }

}
