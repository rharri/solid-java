package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyExchangerImpl extends AbstractCurrencyExchanger implements CurrencyExchanger {

    @Override
    public BigDecimal makeExchange(BigDecimal amountToExchange, SupportedExchange exchange) {
        if (amountToExchange == null) {
            throw new IllegalArgumentException("Amount to exchange can't be null");
        }

        if (amountToExchange.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount to exchange can't be less than or equal to zero");
        }

        if (RateTable.getInstance().noMatch(exchange)) {
            throw new IllegalArgumentException("Exchange is not supported");
        }

        BigDecimal customerRate = getCustomerRate(exchange);

        BigDecimal exchangedAmount = amountToExchange.multiply(customerRate).setScale(8, RoundingMode.HALF_EVEN);

        if (exchangedAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Exchanged amount can't be less than or equal to zero");
        }

        return exchangedAmount;
    }

    @Override
    public BigDecimal getCustomerRate(SupportedExchange exchange) {
        ExchangeRate exchangeRate = RateTable.getInstance().get(exchange);

        BigDecimal customerRate = exchangeRate.rate();
        BigDecimal defaultMargin = RateTable.getInstance().getDefaultMargin();
        if (exchangeRate.rate().compareTo(defaultMargin) > 0) {
            BigDecimal marginAmount = exchangeRate.rate().multiply(defaultMargin);
            customerRate = exchangeRate.rate().add(marginAmount);
        }
        return customerRate;
    }
}
