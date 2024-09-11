package com.ryanharri.solid_java;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import com.ryanharri.solid_java.single_responsibility_principle.ColumnFormat;
import com.ryanharri.solid_java.single_responsibility_principle.ListFormat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.ryanharri.solid_java.single_responsibility_principle.BadDirectoryService;
import com.ryanharri.solid_java.single_responsibility_principle.GoodDirectoryService;

public class DirectoryServiceTest {

    @Test
    public void directoryListingFormattedAsColumnShouldBeEqual(@TempDir Path tempDir) {
        String dirList1 = BadDirectoryService.getInstance(tempDir.toString()).list();

        var gds = GoodDirectoryService.getInstance(tempDir.toString());
        String dirList2 = gds.list(gds.sort(null), new ColumnFormat());

        assertEquals(dirList1, dirList2);
    }
}
