package com.ryanharri.solid_java;

import com.ryanharri.solid_java.open_closed_principle.*;
import com.ryanharri.solid_java.single_responsibility_principle.BadDirectoryService;
import com.ryanharri.solid_java.single_responsibility_principle.GoodDirectoryService;
import com.ryanharri.solid_java.single_responsibility_principle.ListingFormat;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        configureLogger();

        // SRP example
        Path directory = Path.of(System.getProperty("user.home"), "Projects", "jdk");

        String dirList1 = BadDirectoryService.getInstance(directory.toString()).list();
        System.out.print(dirList1);

        System.out.printf("%n");

        GoodDirectoryService goodDirectoryService = GoodDirectoryService.getInstance(directory.toString());
        String dirList2 = goodDirectoryService.list(goodDirectoryService.sort(null), ListingFormat.COLUMN);
        System.out.print(dirList2);

        // OCP example
        List<BadCustomer> badCustomers = new ArrayList<>();
        PhoneNumber phoneNumber = new PhoneNumber("555", "555-5555");
        String emailAddress = "bean@java.dev";
        badCustomers.add(new BadCustomer(phoneNumber, emailAddress, ContactMethod.PHONE));
        badCustomers.add(new BadCustomer(phoneNumber, emailAddress, ContactMethod.SMS));
        badCustomers.add(new BadCustomer(phoneNumber, emailAddress, ContactMethod.EMAIL));

        for (BadCustomer c : badCustomers) {
            c.contact("BadCustomer: payment due");
        }

        List<GoodCustomer> goodCustomers = new ArrayList<>();
        goodCustomers.add(new GoodCustomer(phoneNumber, emailAddress, new Phone(phoneNumber)));
        goodCustomers.add(new GoodCustomer(phoneNumber, emailAddress, new SMS(phoneNumber)));
        goodCustomers.add(new GoodCustomer(phoneNumber, emailAddress, new Email(emailAddress)));

        for (GoodCustomer c : goodCustomers) {
            c.contact("GoodCustomer: payment due");
        }
    }

    private static void configureLogger() throws IOException {
        Handler fileHandler = new FileHandler("%h/solid_java.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getLogger("").addHandler(fileHandler);
    }
}
