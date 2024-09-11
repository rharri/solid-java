package com.ryanharri.solid_java.single_responsibility_principle;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class ColumnFormat extends PathListingFormat {

    private static final int LINE_LENGTH = 80;
    private static final int COLUMN_WIDTH = 30;
    private static final int PADDING = 10;

    @Override
    public String format(Stream<Path> paths) {
        StringBuilder directoryListing = new StringBuilder();

        int tokensPerRow = LINE_LENGTH / (COLUMN_WIDTH + PADDING);

        List<Path> pathsToPrint = paths.toList();
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

            // Line break for next row
            directoryListing.append(String.format("%n"));

            // Slide to the next 'window'
            fromIndex = toIndex;
            toIndex += tokensPerRow;
        }
        return directoryListing.toString();
    }
}
