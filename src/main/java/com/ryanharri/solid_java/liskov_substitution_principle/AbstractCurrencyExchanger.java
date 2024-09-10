package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public abstract class AbstractCurrencyExchanger {
    protected static final BigDecimal DEFAULT_MARGIN = new BigDecimal("0.05");
    protected static final Map<SupportedExchange, ExchangeRate> RATE_TABLE = new HashMap<>();

    static {
        RATE_TABLE.put(
                SupportedExchange.USD_TO_JPY,
                new ExchangeRate(
                        Currency.getInstance(Locale.US),
                        Currency.getInstance(Locale.JAPAN),
                        new BigDecimal("137.0975")));
    }

    public abstract BigDecimal makeExchange(BigDecimal amountToExchange, SupportedExchange exchange);
}
