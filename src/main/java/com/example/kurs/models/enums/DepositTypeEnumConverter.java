package com.example.kurs.models.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DepositTypeEnumConverter implements AttributeConverter<DepositTypeEnum, Long> {

    @Override
    public Long convertToDatabaseColumn(DepositTypeEnum depositTypeEnum) {
        return depositTypeEnum != null ? depositTypeEnum.getId() : null;
    }

    @Override
    public DepositTypeEnum convertToEntityAttribute(Long dbData) {
        return dbData != null ? DepositTypeEnum.fromId(Math.toIntExact(dbData)) : null;
    }

}
