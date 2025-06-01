package com.example.kurs.dto.deposites.report;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportResponseListDTO implements Serializable {
    private String message;
    private List<ReportResponseDTO> reportResponseDTOList;
}
