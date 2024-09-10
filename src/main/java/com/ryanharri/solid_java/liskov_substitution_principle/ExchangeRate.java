package com.ryanharri.solid_java.liskov_substitution_principle;

import java.math.BigDecimal;
import java.util.Currency;

public record ExchangeRate(Currency from, Currency to, BigDecimal rate) {
}
