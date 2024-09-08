package com.ryanharri.solid_java.single_responsibility_principle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

// Single Responsibility Principle - Code that had only one reason to change
public final class BadDirectoryService {

    private static final int LINE_LENGTH = 80;
    private static final int COLUMN_WIDTH = 30;
    private static final int PADDING = 10;
    private static final Logger logger = Logger.getLogger("com.ryanharri.sold_java");

    private final Path path;

    private BadDirectoryService(String path) {
        if (path == null || path.isBlank() || path.isEmpty()) {
            throw new IllegalArgumentException("Path can't be null or empty");
        }
        this.path = Path.of(path);
    }

    public static BadDirectoryService getInstance(String path) {
        return new BadDirectoryService(path);
    }

    // Breaks SRP, this method is responsible for sorting and formatting of the directory listing
    public String list() {
        StringBuilder directoryListing = new StringBuilder();

        int tokensPerRow = LINE_LENGTH / (COLUMN_WIDTH + PADDING);

        try (Stream<Path> paths = Files.list(path)) {
            List<Path> pathsToPrint = paths
                    .filter(p -> p.toFile().isDirectory())
                    .sorted()
                    .toList();


            final int listSize = pathsToPrint.size();
            int fromIndex = 0;
            int toIndex = tokensPerRow;

            while (fromIndex < listSize) {
                if (toIndex >= listSize) {
                    // Bound toIndex to the last index
                    toIndex = listSize % toIndex == 0 ? listSize : listSize - 1;
                }

                for (Path path : pathsToPrint.subList(fromIndex, toIndex)) {
                    directoryListing.append(String.format("%-" + (COLUMN_WIDTH + PADDING) + "s", path));
                }

                directoryListing.append(String.format("%n"));

                // Slide to the next 'window'
                fromIndex = toIndex;
                toIndex += tokensPerRow;
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "directory listing failed", e);
        }

        return directoryListing.toString();
    }
}
