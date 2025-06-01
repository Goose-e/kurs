package com.example.kurs.dto.deposites.get_all_for_user;

import com.example.kurs.models.enums.DepositTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class GetAllUsersDepoResponseDTO implements Serializable {

    private String clienName;
    private LocalDateTime depoOpenDate;
    private LocalDateTime depoCloseDate;
    private String type;
    private BigDecimal balance;
    private String depoCode;
    public GetAllUsersDepoResponseDTO() {
    }

    public GetAllUsersDepoResponseDTO(String depoName,String depoCode, LocalDateTime depoOpenDate, LocalDateTime depoCloseDate, String type, BigDecimal balance) {
        this.clienName = depoName;
        this.depoCode = depoCode;
        this.depoOpenDate = depoOpenDate;
        this.depoCloseDate = depoCloseDate;
        this.type = type;
        this.balance = balance;
    }
}
