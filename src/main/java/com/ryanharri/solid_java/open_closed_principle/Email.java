package com.ryanharri.solid_java.open_closed_principle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Email implements CommunicationMethod {

    private final Logger logger = Logger.getLogger("com.ryanharri.solid_java");

    private final String emailAddress;

    public Email(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void send(String message) {
        String emailMessageTranscript = String.format(
                "email sent to email address %s with message: %s", emailAddress, message);
        logger.log(Level.INFO, emailMessageTranscript);
    }
}
