package com.ryanharri.solid_java.single_responsibility_principle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class GoodDirectoryService {

    private static final int LINE_LENGTH = 80;
    private static final int COLUMN_WIDTH = 30;
    private static final int PADDING = 10;
    private static final Logger logger = Logger.getLogger("com.ryanharri.sold_java");

    private final Path path;

    private GoodDirectoryService(String path) {
        if (path == null || path.isBlank() || path.isEmpty()) {
            throw new IllegalArgumentException("Path can't be null or empty");
        }
        this.path = Path.of(path);
    }

    public static GoodDirectoryService getInstance(String path) {
        return new GoodDirectoryService(path);
    }

    public String list(Stream<Path> paths, ListingFormat listingFormat) {
        return format(paths.filter(p -> p.toFile().isDirectory()), listingFormat);
    }

    public Stream<Path> sort(Comparator<Path> comparator) {
        if (comparator == null) {
            comparator = Comparator.comparing(Path::getFileName);
        }

        Stream<Path> sortedPaths = Stream.empty();
        try {
            sortedPaths = Files.list(path)
                    .filter(p -> p.toFile().isDirectory())
                    .sorted(comparator);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "directory listing failed", e);
        }
        return sortedPaths;
    }

    public String format(Stream<Path> paths, ListingFormat listingFormat) {
        return switch (listingFormat) {
            case COLUMN -> formatAsColumn(paths);
            case LIST -> formatAsList(paths);
        };
    }

    private String formatAsColumn(Stream<Path> paths) {
        StringBuilder directoryListing = new StringBuilder();

        int tokensPerRow = LINE_LENGTH / (COLUMN_WIDTH + PADDING);

        List<Path> pathsToPrint = paths.toList();
        final int listSize = pathsToPrint.size();
        int fromIndex = 0;
        int toIndex = tokensPerRow;

        while (fromIndex < listSize) {
            if (toIndex >= listSize) {
                // bound toIndex to the last index
                toIndex = listSize % toIndex == 0 ? listSize : listSize - 1;
            }

            for (Path path : pathsToPrint.subList(fromIndex, toIndex)) {
                directoryListing.append(String.format("%-" + (COLUMN_WIDTH + PADDING) + "s", path));
            }

            // line break for next row
            directoryListing.append(String.format("%n"));

            // slide to the next 'window'
            fromIndex = toIndex;
            toIndex += tokensPerRow;
        }
        return directoryListing.toString();
    }

    private String formatAsList(Stream<Path> paths) {
        StringBuilder directoryListing = new StringBuilder();

        for (Path path : paths.toList()) {
            directoryListing.append(String.format("%s%n", path.toFile().getName()));
        }

        // remove last line break
        directoryListing.replace(directoryListing.length() - 1, directoryListing.length(), "");

        return directoryListing.toString();
    }
}
