package com.ryanharri.solid_java;

import com.ryanharri.solid_java.dependency_inversion_principle.*;
import com.ryanharri.solid_java.interface_segregation_principle.ComicBookReader;
import com.ryanharri.solid_java.interface_segregation_principle.EBookReader;
import com.ryanharri.solid_java.interface_segregation_principle.KindleReader;
import com.ryanharri.solid_java.liskov_substitution_principle.*;
import com.ryanharri.solid_java.open_closed_principle.*;
import com.ryanharri.solid_java.single_responsibility_principle.BadDirectoryService;
import com.ryanharri.solid_java.single_responsibility_principle.ColumnFormat;
import com.ryanharri.solid_java.single_responsibility_principle.GoodDirectoryService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Main {
    public static void main(String[] args) throws IOException {
        configureLogger();

        srpExample(); // S

        ocpExample(); // O

        lspExample(); // L

        ispExample(); // I

        dipExample(); // D
    }

    private static void dipExample() {
        List<LogEntry> logEntries = List.of(
                new LogEntry(Level.INFO, "Loading class", "2024-09-09"),
                new LogEntry(Level.WARNING, "Class contains invalid metadata", "2024-09-09"),
                new LogEntry(Level.SEVERE, "Class failed to load", "2024-09-09")
        );

        for (LogEntry entry : logEntries) {
            BadLog.print(entry);
        }

        // Possible solution
        for (LogEntry entry : logEntries) {
            Log.print(entry, new LongDateParser("yyyy-MM-dd"));
        }

        for (LogEntry entry : logEntries) {
            Log.print(entry, new ShortDateParser("yyyy-MM-dd"));
        }
    }

    private static void ispExample() {
        // ISP example
        EBookReader comicBookReader = new ComicBookReader(1024);
        comicBookReader.playSound();
        comicBookReader.playVideo();

        EBookReader kindleReader = new KindleReader(1024);
        kindleReader.playSound();
        kindleReader.playVideo();
    }

    private static void lspExample() {
        // LSP example
        List<BadQuote> badQuotes = new ArrayList<>();
        badQuotes.add(new BadQuote("foo@fx.com", new CurrencyExchange()));
        badQuotes.add(new BadQuote("bar@fx.com", new BadLargeCurrencyExchange())); // will throw

        for (BadQuote q : badQuotes) {
            q.displayCustomerRate(SupportedExchange.USD_TO_JPY);

            // BadLargeCurrencyExchange cannot be substituted for CurrencyExchange
            q.sendQuote(new BigDecimal("500.00"), SupportedExchange.USD_TO_JPY);
        }

        // Possible solution
        GoodQuote goodQuote = new GoodQuote("james@fx.com");

        List<CurrencyExchanger> currencyExchangers = new ArrayList<>();
        currencyExchangers.add(new CurrencyExchangerImpl());
        currencyExchangers.add(new LargeCurrencyExchanger());

        // Every CurrencyExchanger is expected to provide an exchange rate
        for (CurrencyExchanger c : currencyExchangers) {
            goodQuote.displayCustomerRate(SupportedExchange.USD_TO_JPY, c);
        }

        // Different AbstractCurrencyExchanger's can provide different quotes
        goodQuote.sendQuote(new BigDecimal("500.00"), SupportedExchange.USD_TO_JPY, new CurrencyExchangerImpl());
        goodQuote.sendQuote(new BigDecimal("150000.00"), SupportedExchange.USD_TO_JPY, new LargeCurrencyExchanger());
    }

    private static void ocpExample() {
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

        // Possible solution
        List<GoodCustomer> goodCustomers = new ArrayList<>();
        goodCustomers.add(new GoodCustomer(phoneNumber, emailAddress, new Phone(phoneNumber)));
        goodCustomers.add(new GoodCustomer(phoneNumber, emailAddress, new SMS(phoneNumber)));
        goodCustomers.add(new GoodCustomer(phoneNumber, emailAddress, new Email(emailAddress)));

        for (GoodCustomer c : goodCustomers) {
            c.contact("GoodCustomer: payment due");
        }
    }

    private static void srpExample() {
        // SRP example
        Path directory = Path.of(System.getProperty("user.home"), "Projects", "jdk");

        String dirList1 = BadDirectoryService.getInstance(directory.toString()).list();
        System.out.print(dirList1);

        System.out.printf("%n");

        // Possible solution
        GoodDirectoryService goodDirectoryService = GoodDirectoryService.getInstance(directory.toString());
        String dirList2 = goodDirectoryService.list(goodDirectoryService.sort(null), new ColumnFormat());
        System.out.print(dirList2);
    }

    private static void configureLogger() throws IOException {
        Handler fileHandler = new FileHandler("%h/solid_java.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getLogger("").addHandler(fileHandler);
    }
}
