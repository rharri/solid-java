package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;

public interface CurrencyExchanger {
    BigDecimal getCustomerRate(SupportedExchange exchange);
}
