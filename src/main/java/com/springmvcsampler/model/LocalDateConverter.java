package com.springmvcsampler.model;

import javax.persistence.AttributeConverter;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by atheedom on 10/04/15.
 */
//@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(@NotNull LocalDate date) {
        Instant instant = Instant.from(date);
        return Date.from(instant);
    }

    @Override
    public LocalDate convertToEntityAttribute(@NotNull Date value) {
        Instant instant = value.toInstant();
        return LocalDate.from(instant);
    }
}
