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
        if (dbData == null) return null;
        for (DepositTypeEnum type : DepositTypeEnum.values()) {
            if (type.getId().equals(dbData)) return type;
        }
        throw new IllegalArgumentException("Unknown deposit type ID: " + dbData);
    }

}
