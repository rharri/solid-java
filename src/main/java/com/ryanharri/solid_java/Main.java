package com.ryanharri.solid_java;

import com.ryanharri.solid_java.single_responsibility_principle.BadDirectoryService;
import com.ryanharri.solid_java.single_responsibility_principle.ListingFormat;
import com.ryanharri.solid_java.single_responsibility_principle.GoodDirectoryService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] args) throws IOException {
        configureLogger();

        Path directory = Path.of(System.getProperty("user.home"), "Projects", "jdk");

        String dirList1 = BadDirectoryService.getInstance(directory.toString()).list();
        System.out.print(dirList1);

        System.out.printf("%n");

        GoodDirectoryService goodDirectoryService = GoodDirectoryService.getInstance(directory.toString());
        String dirList2 = goodDirectoryService.list(goodDirectoryService.sort(null), ListingFormat.COLUMN);
        System.out.print(dirList2);
    }

    private static void configureLogger() throws IOException {
        Handler fileHandler = new FileHandler("%h/solid_java.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getLogger("").addHandler(fileHandler);
    }
}
