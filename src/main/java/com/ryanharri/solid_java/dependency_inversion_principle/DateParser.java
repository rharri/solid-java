package com.ryanharri.solid_java.dependency_inversion_principle;

import java.time.LocalDate;

public interface DateParser {
    LocalDate parse(String date);
    String format(LocalDate date);
}
