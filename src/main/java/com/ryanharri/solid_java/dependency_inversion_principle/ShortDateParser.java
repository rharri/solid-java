package com.ryanharri.solid_java.dependency_inversion_principle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public record ShortDateParser(String pattern) implements DateParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

    public LocalDate parse(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern()));
    }

    public String format(LocalDate date) {
        return DATE_TIME_FORMATTER.format(date);
    }
}
