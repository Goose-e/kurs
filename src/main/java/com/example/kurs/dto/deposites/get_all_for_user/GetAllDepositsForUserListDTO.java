package com.example.kurs.dto.deposites.get_all_for_user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetAllDepositsForUserListDTO implements Serializable {

    private List<GetAllUsersDepoResponseDTO> allDeposits;
    private String message;
    public GetAllDepositsForUserListDTO(List<GetAllUsersDepoResponseDTO> allDeposits) {
        this.allDeposits = allDeposits;
    }

    public GetAllDepositsForUserListDTO() {
    }
}
