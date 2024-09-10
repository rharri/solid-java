package com.ryanharri.solid_java.dependency_inversion_principle;

import java.util.logging.Level;

public record LogEntry(Level level, String detail, String date) {
}
