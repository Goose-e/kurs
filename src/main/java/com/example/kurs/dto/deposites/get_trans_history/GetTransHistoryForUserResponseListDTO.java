package com.example.kurs.dto.deposites.get_trans_history;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetTransHistoryForUserResponseListDTO implements Serializable {
    private List<GetTransHistoryForUserResponseDTO> transHistoryForUserResponseDTOList;
    private String message;

    public GetTransHistoryForUserResponseListDTO() {
    }

    public GetTransHistoryForUserResponseListDTO(List<GetTransHistoryForUserResponseDTO> transHistoryForUserResponseDTOList) {
        this.transHistoryForUserResponseDTOList = transHistoryForUserResponseDTOList;
    }
}
