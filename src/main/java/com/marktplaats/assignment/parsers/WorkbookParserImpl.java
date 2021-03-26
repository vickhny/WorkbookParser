package com.marktplaats.assignment.parsers;

import com.marktplaats.assignment.common.FileProcessor;
import com.marktplaats.assignment.common.FileType;

import org.springframework.stereotype.Component;

@Component
public class WorkbookParserImpl implements WorkbookParser {

    @Override
    public FileParser buildFileParser(String fileName) {
        FileType type = FileProcessor.type(fileName);
        FileParser parser = null;

        switch (type) {
            case CSV:
                parser = new CSVParser();
                break;
            case PRN:
                parser = new PRNParser();
                break;
        }
        return parser;
    }
}
