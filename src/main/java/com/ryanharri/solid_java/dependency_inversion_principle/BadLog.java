package com.ryanharri.solid_java.dependency_inversion_principle;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

// Dependency Inversion Principle - Do not depend on concrete implementations, depend on abstractions
public class BadLog {

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);

    // This method depends on the concrete parseLongDate implementation
    public static void print(LogEntry logEntry) {
        String longDate = parseLongDate(logEntry.date());
        System.out.printf("%s: [%s] %s%n", longDate, logEntry.level().getName(), logEntry.detail());
    }

    private static String parseLongDate(String date) {
        return DATE_TIME_FORMATTER.format(LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE));
    }
}
