package com.ryanharri.solid_java.open_closed_principle;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SMS implements CommunicationMethod {

    private final Logger logger = Logger.getLogger("com.ryanharri.solid_java");

    private final PhoneNumber phoneNumber;

    public SMS(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        String textMessageTranscript = String.format(
                "sms sent to phone number %s with message: %s", phoneNumber, message);
        logger.log(Level.INFO, textMessageTranscript);
    }
}
