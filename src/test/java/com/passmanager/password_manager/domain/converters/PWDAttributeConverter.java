package com.passmanager.password_manager.domain.converters;

import com.passmanager.password_manager.domain.vo.Password;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PWDAttributeConverter implements AttributeConverter<Password, String> {
    @Override
    public String convertToDatabaseColumn(Password attribute) {
        

        return attribute == null ? null : attribute.toString();
    }

    @Override
    public Password convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new Password(dbData);
    }
}