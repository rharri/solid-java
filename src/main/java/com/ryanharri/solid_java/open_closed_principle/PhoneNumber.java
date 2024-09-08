package com.ryanharri.solid_java.open_closed_principle;

public record PhoneNumber(String areaCode, String number) {

    @Override
    public String toString() {
        return String.format("(%s)%s", areaCode(), number());
    }
}
