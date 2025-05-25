package com.example.kurs.models.enums;


import com.example.kurs.models.DepositTypes;
import com.example.kurs.repo.DepositTypesRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EnumInit {
    private final DepositTypesRepo depositTypesRepo;

    @PostConstruct
    public void initAllRefs() {
        initDepositType();

    }

    private void initDepositType() {
        for (DepositTypeEnum depEnum : DepositTypeEnum.values()) {
            if (!depositTypesRepo.existsById(depEnum.getId())) {

                DepositTypes depositTypes = DepositTypes.builder()
                        .type_id(depEnum.getId())
                        .name(depEnum.getName())
                        .description(depEnum.getDescription())
                        .interest_rate(depEnum.getInterestRate())
                        .term_months(depEnum.getTermMonths())
                        .can_withdraw(depEnum.isCanWithdraw())
                        .can_add_funds(depEnum.isCanAddFunds())
                        .build();


                depositTypesRepo.save(depositTypes);
            }
        }
    }
}
