package com.example.kurs.models;


import jakarta.persistence.*;
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
    @Column(name = "type_id")
    private Long typeId;
    private String name;
    private String description;
    private Double interest_rate;
    private Integer term_months;
    private Boolean can_withdraw;
    private Boolean can_add_funds;
}
