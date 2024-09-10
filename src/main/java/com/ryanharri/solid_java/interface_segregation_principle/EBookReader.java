package com.ryanharri.solid_java.interface_segregation_principle;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class EBookReader {

    private final Set<Integer> bookmarks;

    protected final int storageSize;
    protected int currentPage;
    protected int totalPages;

    public EBookReader(int storageSize) {
        this.storageSize = storageSize;
        this.bookmarks = new HashSet<>();
        this.currentPage = 0;
        this.totalPages = 0;
    }

    public void open(Path pathToEBook) throws IOException {
        try (BufferedReader ebook = Files.newBufferedReader(pathToEBook)) {

            // Set total pages
            totalPages = 200;

            System.out.println("Opened ebook");
        }
    }

    public void print(int fromPageNumber, int toPageNumber) {
        System.out.printf("Printing from page %d to page %d%n", fromPageNumber, toPageNumber);
    }

    public void previousPage() {
        if (currentPage == 0) {
            return;
        }
        currentPage -= 1;
    };

    public void nextPage() {
        if (currentPage >= totalPages) {
            return;
        }
        currentPage += 1;
    };

    public abstract void playSound();

    public abstract void playVideo();

    public void addBookmark() {
        bookmarks.add(currentPage);
        System.out.println("Added bookmark");
    }

    public Set<Integer> getBookmarks() {
        return Collections.unmodifiableSet(bookmarks);
    }
}
