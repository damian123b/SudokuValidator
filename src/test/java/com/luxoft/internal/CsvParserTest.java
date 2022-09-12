package com.luxoft.internal;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CsvParserTest {

    @Test(expected = FileNotFoundException.class)
    public void shouldThrowExceptionWhenReadingNonExistingFile() throws IOException {
        new CsvParser().parseCsv(new File("no_such_file.csv"));
    }
}
