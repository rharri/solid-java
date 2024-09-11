package com.ryanharri.solid_java.single_responsibility_principle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class GoodDirectoryService {

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

    public String list(Stream<Path> paths, PathListingFormat pathListingFormat) {
        return format(paths.filter(p -> p.toFile().isDirectory()), pathListingFormat);
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

    public String format(Stream<Path> paths, PathListingFormat pathListingFormat) {
        return pathListingFormat.format(paths);
    }
}
