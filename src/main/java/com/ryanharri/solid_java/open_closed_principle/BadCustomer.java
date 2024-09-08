package com.ryanharri.solid_java.open_closed_principle;

// Open Closed Principle - Open for extension, closed for modification
// Change what modules do, without changing the source code of the module
public record BadCustomer(PhoneNumber phoneNumber, String emailAddress,
                          ContactMethod preferredContactMethod) {

    // Violates OCP, this method must be modified if new contact methods become available
    public void contact(String message) {
        switch (preferredContactMethod) {
            case EMAIL -> new Email(emailAddress).send(message);
            case PHONE -> new Phone(phoneNumber).send(message);
            case SMS -> new SMS(phoneNumber).send(message);
        }
    }
}
