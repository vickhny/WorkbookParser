package com.marktplaats.assignment.service;

import com.marktplaats.assignment.model.CreditLimitConflict;
import com.marktplaats.assignment.model.ProcessedRecord;
import com.marktplaats.assignment.parsers.WorkbookParser;
import com.marktplaats.assignment.parsers.WorkbookParserImpl;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreditLimitTrackerServiceTest {

    private CreditLimitTrackerService trackerService;

    @Before
    public void setUp() {
        WorkbookParser workbookParser = new WorkbookParserImpl();
        trackerService = new CreditLimitTrackerService(workbookParser);
    }

    @Test
    public void convertToHtmlTest() throws IOException, URISyntaxException {

        File file = getFileFromResource("testscvfile");

        InputStream inputStream = new FileInputStream(file);

        MockMultipartFile multipartFile =
                new MockMultipartFile(
                        "test.csv",
                        "test.csv",
                        MediaType.MULTIPART_FORM_DATA_VALUE,
                        inputStream);

        List<ProcessedRecord> processedRecords = trackerService.convertToHtml(new MultipartFile[]{multipartFile});
        assertEquals(1, processedRecords.size());
        assertEquals(7, processedRecords.get(0).getRecords().size());
        assertEquals("\"Johnson, John\"", processedRecords.get(0).getRecords().get(0).getName());
    }

    @Test
    public void findRecordsWithCreditConflictTest() throws URISyntaxException, IOException {

        File csvfile = getFileFromResource("testscvfile");
        File prnfile = getFileFromResource("testprnfile");

        InputStream csvInputStream = new FileInputStream(csvfile);
        InputStream prnInputStream = new FileInputStream(prnfile);

        MockMultipartFile csvMultipartFile =
                new MockMultipartFile(
                        "test.csv",
                        "test.csv",
                        MediaType.MULTIPART_FORM_DATA_VALUE,
                        csvInputStream);

        MockMultipartFile prnMultipartFile =
                new MockMultipartFile(
                        "test.prn",
                        "test.prn",
                        MediaType.MULTIPART_FORM_DATA_VALUE,
                        prnInputStream);

        List<ProcessedRecord> processedRecords = trackerService.convertToHtml(new MultipartFile[]{csvMultipartFile, prnMultipartFile});
        List<CreditLimitConflict> conflicts = trackerService.findRecordsWithCreditConflict(processedRecords);
        assertEquals(2, processedRecords.size());
        assertEquals(1, conflicts.size());
        assertEquals("54", conflicts.get(0).getCreditLimit());
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
