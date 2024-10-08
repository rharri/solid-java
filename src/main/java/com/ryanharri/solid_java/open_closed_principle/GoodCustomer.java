package com.ryanharri.solid_java.open_closed_principle;

public record GoodCustomer(PhoneNumber phoneNumber, String emailAddress,
                           CommunicationMethod preferredContactMethod) {

    public void contact(String message) {
        preferredContactMethod.send(message);
    }
}
