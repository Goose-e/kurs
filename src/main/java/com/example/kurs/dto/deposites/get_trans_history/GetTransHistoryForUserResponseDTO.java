package com.example.kurs.dto.deposites.get_trans_history;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GetTransHistoryForUserResponseDTO implements Serializable {
    private String depositCode;
    private LocalDateTime operationDate;
    private String operationType;
    private BigDecimal amount;

    public GetTransHistoryForUserResponseDTO() {
    }

    public GetTransHistoryForUserResponseDTO(String depositCode, LocalDateTime operationDate, String operationType, BigDecimal amount) {
        this.depositCode = depositCode;
        this.operationDate = operationDate;
        this.operationType = operationType;
        this.amount = amount;
    }
}
