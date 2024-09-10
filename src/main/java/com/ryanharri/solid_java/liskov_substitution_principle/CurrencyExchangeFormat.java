package com.ryanharri.solid_java.liskov_substitution_principle;

import java.text.NumberFormat;
import java.util.Locale;

public record CurrencyExchangeFormat(NumberFormat from, NumberFormat to) {

    private static final NumberFormat US_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);
    private static final NumberFormat JP_FORMAT = NumberFormat.getCurrencyInstance(Locale.JAPAN);
    private static final int DEFAULT_FRACTION_DIGITS = 2;

    static {
        US_FORMAT.setMinimumFractionDigits(DEFAULT_FRACTION_DIGITS);
        JP_FORMAT.setMinimumFractionDigits(DEFAULT_FRACTION_DIGITS);
    }

    public static CurrencyExchangeFormat getFormat(SupportedExchange exchange) {
        return switch (exchange) {
            case USD_TO_JPY -> new CurrencyExchangeFormat(US_FORMAT, JP_FORMAT);
        };
    }
}
