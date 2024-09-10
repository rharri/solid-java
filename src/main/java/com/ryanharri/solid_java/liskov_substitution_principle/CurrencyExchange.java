package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CurrencyExchange {

    private static final BigDecimal DEFAULT_MARGIN = new BigDecimal("0.05");
    protected static final Map<SupportedExchange, ExchangeRate> RATE_TABLE = new HashMap<>();

    static {
        RATE_TABLE.put(
                SupportedExchange.USD_TO_JPY,
                new ExchangeRate(
                        Currency.getInstance(Locale.US),
                        Currency.getInstance(Locale.JAPAN),
                        new BigDecimal("137.0975")));
    }

    public BigDecimal makeExchange(BigDecimal amountToExchange, SupportedExchange exchange) {
        if (amountToExchange == null) {
            throw new IllegalArgumentException("Amount to exchange can't be null");
        }

        if (amountToExchange.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount to exchange can't be less than or equal to zero");
        }

        if (!RATE_TABLE.containsKey(exchange)) {
            throw new IllegalArgumentException("Exchange is not supported");
        }

        BigDecimal customerRate = getCustomerRate(exchange);

        BigDecimal exchangedAmount = amountToExchange.multiply(customerRate).setScale(8, RoundingMode.HALF_EVEN);

        if (exchangedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Exchanged amount can't be less than or equal to zero");
        }

        return exchangedAmount;
    }

    protected BigDecimal getCustomerRate(SupportedExchange exchange) {
        ExchangeRate exchangeRate = RATE_TABLE.get(exchange);

        BigDecimal customerRate = exchangeRate.rate();
        if (exchangeRate.rate().compareTo(DEFAULT_MARGIN) > 0) {
            BigDecimal marginAmount = exchangeRate.rate().multiply(DEFAULT_MARGIN);
            customerRate = exchangeRate.rate().add(marginAmount);
        }
        return customerRate;
    }
}
