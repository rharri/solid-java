package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public record GoodQuote(String emailAddress) {

    private static final Logger logger = Logger.getLogger("com.ryanharri.solid_java");

    public void sendQuote(BigDecimal amount, SupportedExchange exchange, AbstractCurrencyExchanger currencyExchange) {
        try {
            BigDecimal exchangedAmount = currencyExchange.makeExchange(amount, exchange);

            CurrencyExchangeFormat exchangeFormat = CurrencyExchangeFormat.getFormat(exchange);

            System.out.printf(
                    "Email sent for %s: %s ⇨ %s %n",
                    emailAddress(),
                    exchangeFormat.from().format(amount),
                    exchangeFormat.to().format(exchangedAmount));

        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, "exchange failed", e);
        }
    }

    public void displayCustomerRate(SupportedExchange exchange, CurrencyExchanger currencyExchanger) {
        BigDecimal customerRate = currencyExchanger.getCustomerRate(exchange);

        System.out.printf("Customer rate for %s: %s%n", emailAddress(), customerRate);
    }
}
