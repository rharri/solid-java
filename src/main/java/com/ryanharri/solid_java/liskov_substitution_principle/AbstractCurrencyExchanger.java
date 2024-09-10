package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;

public abstract class AbstractCurrencyExchanger {
    public abstract BigDecimal makeExchange(BigDecimal amountToExchange, SupportedExchange exchange);
}
