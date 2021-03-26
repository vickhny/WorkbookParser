package com.marktplaats.assignment.parser;

import com.marktplaats.assignment.model.ProcessedRecord;
import com.marktplaats.assignment.parsers.CSVParser;

import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class AbstractFileParserTest {

    @Test
    public void parseInvalidLineRecordTest() throws URISyntaxException {
        CSVParser parser = new CSVParser();

        File file = getFileFromResource("invalid.csv");
        ProcessedRecord processedRecord = parser.parse(file);

        assertEquals(1, processedRecord.getRecords().size());
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}