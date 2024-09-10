package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;
import java.math.RoundingMode;

// Liskov Substitution Principle - Subtype should behave like supertype, when it's used like a supertype
// Behavioural subtyping - Subtypes behave like supertypes
public class BadLargeCurrencyExchange extends CurrencyExchange {

    // Violates LSP as the preconditions are stronger than those of the supertype's method
    // Code that is written to the supertype's interface may be surprised by the behaviour of this method
    // Reasoning about the supertype's behaviour is more difficult when the subtype behaves unexpectedly
    @Override
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

        // Violates LSP
        if (amountToExchange.compareTo(new BigDecimal("100000.00")) < 0) {
            throw new IllegalArgumentException("Amount to exchange must be greater than 100000.00");
        }

        BigDecimal customerRate = getCustomerRate(exchange);

        BigDecimal exchangedAmount = amountToExchange.multiply(customerRate).setScale(8, RoundingMode.HALF_EVEN);

        if (exchangedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Exchanged amount can't be less than or equal to zero");
        }

        return exchangedAmount;
    }

    @Override
    protected BigDecimal getCustomerRate(SupportedExchange exchange) {
        // No margin on large exchanges
        return RATE_TABLE.get(exchange).rate();
    }
}
