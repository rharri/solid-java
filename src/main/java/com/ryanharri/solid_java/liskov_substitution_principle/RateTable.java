package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;
import java.util.*;

public final class RateTable {

    private static RateTable instance;

    private final BigDecimal defaultMargin;
    private final Map<SupportedExchange, ExchangeRate> rates = new HashMap<>();

    private RateTable() {
        defaultMargin = new BigDecimal("0.05");

        rates.put(
                SupportedExchange.USD_TO_JPY,
                new ExchangeRate(
                        Currency.getInstance(Locale.US),
                        Currency.getInstance(Locale.JAPAN),
                        new BigDecimal("137.0975")));
    }

    public static RateTable getInstance() {
        if (instance == null) {
            synchronized (RateTable.class) {
                if (instance == null) {
                    instance = new RateTable();
                }
            }
        }
        return instance;
    }

    public BigDecimal getDefaultMargin() {
        return new BigDecimal(String.valueOf(defaultMargin));
    }

    public ExchangeRate get(SupportedExchange exchange) {
        return rates.get(exchange);
    }

    public boolean noMatch(SupportedExchange exchange) {
        return !rates.containsKey(exchange);
    }
}
