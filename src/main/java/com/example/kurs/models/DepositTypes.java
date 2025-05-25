package com.example.kurs.models;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "deposit_types")
@NoArgsConstructor
@AllArgsConstructor
public class DepositTypes {
    @Id
    private Long type_id;
    private String name;
    private String description;
    private Double interest_rate;
    private Integer term_months;
    private Boolean can_withdraw;
    private Boolean can_add_funds;
}
