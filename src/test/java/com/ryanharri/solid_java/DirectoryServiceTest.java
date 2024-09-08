package com.ryanharri.solid_java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.ryanharri.solid_java.single_responsibility_principle.BadDirectoryService;
import com.ryanharri.solid_java.single_responsibility_principle.ListingFormat;
import com.ryanharri.solid_java.single_responsibility_principle.GoodDirectoryService;

public class DirectoryServiceTest {

    @Test
    public void directoryListingsShouldBeEqual(@TempDir Path tempDir) {        
        String dirList1 = BadDirectoryService.getInstance(tempDir.toString()).list();

        var gds = GoodDirectoryService.getInstance(tempDir.toString());
        String dirList2 = gds.list(gds.sort(null), ListingFormat.COLUMN);

        assertEquals(dirList1, dirList2);
    }
}
