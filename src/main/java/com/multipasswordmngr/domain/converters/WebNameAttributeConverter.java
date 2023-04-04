package com.multipasswordmngr.domain.converters;

import com.multipasswordmngr.domain.vo.WebsiteName;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class WebNameAttributeConverter  implements AttributeConverter<WebsiteName, String>{
    @Override
    public String convertToDatabaseColumn(WebsiteName attribute) {
        return attribute == null ? null : attribute.toString();
    }

    @Override
    public WebsiteName convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new WebsiteName(dbData);
    }
}
