package com.ryanharri.solid_java.dependency_inversion_principle;

import java.time.LocalDate;

public class Log {

    public static void print(LogEntry logEntry, DateParser dateParser) {
        LocalDate date = dateParser.parse(logEntry.date());
        System.out.printf("%s: [%s] %s%n", dateParser.format(date), logEntry.level().getName(), logEntry.detail());
    }
}
