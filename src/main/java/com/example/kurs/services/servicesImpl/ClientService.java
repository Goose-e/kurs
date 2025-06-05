package com.example.kurs.services.servicesImpl;

import com.example.kurs.config.jwt.CustomAuthenticationToken;
import com.example.kurs.dao.ClientDao;
import com.example.kurs.dto.clients.create.CreateClientRequestDTO;
import com.example.kurs.dto.clients.create.CreateClientResponse;
import com.example.kurs.dto.clients.create.CreateClientResponseDTO;
import com.example.kurs.dto.clients.edit.EditClientRequestDTO;
import com.example.kurs.dto.clients.edit.EditClientResponse;
import com.example.kurs.dto.clients.edit.EditClientResponseDTO;
import com.example.kurs.dto.clients.find.FindClientsByUsernameRequestDTO;
import com.example.kurs.dto.clients.find.FindClientsByUsernameResponse;
import com.example.kurs.dto.clients.find.FindClientsByUsernameResponseDTO;
import com.example.kurs.dto.clients.find.FindClientsByUsernameResponseListDTO;
import com.example.kurs.mapper.ClientMapper;
import com.example.kurs.models.Client;
import com.example.kurs.models.User;
import com.example.kurs.services.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {
    private final ClientDao clientDao;
    private final ClientMapper clientMapper;


    @Override
    public ResponseEntity<CreateClientResponseDTO> createClient(CreateClientRequestDTO createClientRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CreateClientResponseDTO responseDTO = new CreateClientResponseDTO();
        HttpStatusCode code;
        if (authentication instanceof CustomAuthenticationToken customToken) {
            String userCode = customToken.getCode();
            Client client = clientDao.findClientByCode(userCode);
            if (client == null) {
                User user = clientDao.findUserIdByUserCode(userCode);

                client = clientMapper.mapCreateClientToClient(createClientRequestDTO, user);

                System.out.println(client);

                clientDao.saveClient(client);
                responseDTO = clientMapper.mapClientToCreatedResponseDTO(client, user);
                code = HttpStatus.CREATED;
            } else {
                responseDTO.setMessage("Client already exists");
                code = HttpStatus.OK;
            }
        } else {
            code = HttpStatus.UNAUTHORIZED;
        }

        return new CreateClientResponse(responseDTO, code);
    }

    @Override
    public ResponseEntity<FindClientsByUsernameResponseListDTO> findClientsByUsername(FindClientsByUsernameRequestDTO findClientsByUsernameRequestDTO) {
        List<FindClientsByUsernameResponseDTO> clients = clientDao.findClientsByUsernameOrFullName(findClientsByUsernameRequestDTO.getUsernameOrFullName());
        HttpStatusCode code;
        FindClientsByUsernameResponseListDTO responseDTO = new FindClientsByUsernameResponseListDTO();
        if (clients.isEmpty()) {
            responseDTO.setMessage("No clients found");
            code = HttpStatus.OK;
        } else {
            responseDTO.setClients(clients);
            code = HttpStatus.OK;
            responseDTO.setMessage("Found " + clients.size() + " clients");
        }
        return new FindClientsByUsernameResponse(responseDTO, code);
    }

    @Override
    public ResponseEntity<EditClientResponseDTO> editClients(EditClientRequestDTO editClientRequestDTO) {
        HttpStatusCode code;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        EditClientResponseDTO responseDTO = new EditClientResponseDTO();
        if (authentication instanceof CustomAuthenticationToken customToken) {
            String userCode = customToken.getCode();
            Client client = clientDao.findClientByCode(userCode);
            if (client == null) {
                responseDTO.setMessage("Client does not exist");
                code = HttpStatus.OK;
            } else {
                client = clientMapper.mapClientToChangedClient(client, editClientRequestDTO);
                clientDao.saveClient(client);
                responseDTO = clientMapper.mapClientToChangedClientDTO(client);
                code = HttpStatus.OK;
            }
        } else {
            code = HttpStatus.UNAUTHORIZED;
            responseDTO.setMessage("Unauthorized");
        }
        return new EditClientResponse(responseDTO, code);
    }
}
