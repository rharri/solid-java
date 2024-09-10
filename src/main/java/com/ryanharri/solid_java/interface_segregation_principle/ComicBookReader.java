package com.ryanharri.solid_java.interface_segregation_principle;

import java.util.logging.Level;
import java.util.logging.Logger;

// Interface Segregation Principle - Prefer narrow interfaces to those that provide too wide of an API in which
// implementing classes cannot satisfy the contract
public class ComicBookReader extends EBookReader {

    private final Logger logger = Logger.getLogger("com.ryanharri.solid_java");

    public ComicBookReader(int storageSize) {
        super(storageSize);
    }

    @Override
    public void playSound() {
        logger.log(Level.INFO, "This device does not play sound");
    }

    @Override
    public void playVideo() {
        logger.log(Level.INFO, "This device does not play video");
    }
}
