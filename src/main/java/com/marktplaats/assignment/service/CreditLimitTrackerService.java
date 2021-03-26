package com.marktplaats.assignment.service;

import com.marktplaats.assignment.common.FileProcessor;
import com.marktplaats.assignment.exception.InvalidInputException;
import com.marktplaats.assignment.model.CreditLimitConflict;
import com.marktplaats.assignment.model.ProcessedRecord;
import com.marktplaats.assignment.parsers.FileParser;
import com.marktplaats.assignment.parsers.WorkbookParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditLimitTrackerService {

    private final WorkbookParser workbookParser;

    @Autowired
    public CreditLimitTrackerService(WorkbookParser workbookParser) {
        this.workbookParser = workbookParser;
    }

    public List<ProcessedRecord> convertToHtml(MultipartFile[] multipartFiles) {

        if (multipartFiles.length == 0) {
            throw new InvalidInputException("No input file!!");
        }

        return Arrays.stream(multipartFiles)
                .parallel()
                .map(multipartFile -> FileProcessor.convert(multipartFile))
                .map(file -> {
                    FileParser parser = workbookParser.buildFileParser(file.getName());
                    return parser.parse(file);
                }).collect(Collectors.toList());
    }

    public List<CreditLimitConflict> findRecordsWithCreditConflict(List<ProcessedRecord> processedRecordList) {
        List<CreditLimitConflict> conflicts = new ArrayList<>();

        for (int i = 0; i < processedRecordList.size() - 1; i++) {

            ProcessedRecord workbook = processedRecordList.get(i);

            for (int j = i + 1; j < processedRecordList.size(); j++) {

                ProcessedRecord anotherWorkbook = processedRecordList.get(j);

                workbook.getRecords()
                        .stream().
                        forEach(record -> anotherWorkbook
                                .findRecord(record)
                                .filter(anotherL -> !record.hasEqualCreditLimit(anotherL))
                                .ifPresent(anotherL -> conflicts.add(new CreditLimitConflict(anotherL, record.getSourceWorkbook(), record.getCreditLimitValue()))));
            }
        }
        return conflicts;
    }
}
