package com.ryanharri.solid_java.single_responsibility_principle;

import java.nio.file.Path;
import java.util.stream.Stream;

public class ListFormat extends PathListingFormat {

    @Override
    public String format(Stream<Path> paths) {
        StringBuilder directoryListing = new StringBuilder();

        for (Path path : paths.toList()) {
            directoryListing.append(String.format("%s%n", path));
        }

        // Remove last line break
        directoryListing.replace(directoryListing.length() - 1, directoryListing.length(), "");

        return directoryListing.toString();
    }
}
