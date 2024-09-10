package com.ryanharri.solid_java.interface_segregation_principle;

public class KindleReader extends EBookReader {

    public KindleReader(int storageSize) {
        super(storageSize);
    }

    @Override
    public void playSound() {
        System.out.printf("Playing sound %s%n", Character.toString(0x1D161));
    }

    @Override
    public void playVideo() {
        System.out.printf("Playing video %s%n", Character.toString(0x1F4F9));
    }
}
