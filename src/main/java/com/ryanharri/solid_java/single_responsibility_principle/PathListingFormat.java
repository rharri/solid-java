package com.ryanharri.solid_java.single_responsibility_principle;

import java.nio.file.Path;
import java.util.stream.Stream;

public abstract class PathListingFormat {
    public abstract String format(Stream<Path> paths);
}
