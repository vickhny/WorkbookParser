package com.marktplaats.assignment.parsers;

import com.marktplaats.assignment.common.FileProcessor;
import com.marktplaats.assignment.common.FileType;
import com.marktplaats.assignment.model.CreditLimitRecord;
import com.marktplaats.assignment.model.ProcessedRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractFileParser implements FileParser {

    @Override
    public ProcessedRecord parse(File file) {
        try {
            FileType type = FileProcessor.type(file);
            return new ProcessedRecord(file.getName(), parseWorkbook(file), type);
        } catch (IOException e) {
            log.debug("Something went wrong wile processing file - %d", file.getName());
        }
        return null;
    }

    private List<CreditLimitRecord> parseWorkbook(File file) throws IOException {

        List<CreditLimitRecord> creditLimitRecords = Files.lines(file.toPath(), StandardCharsets.ISO_8859_1).skip(1).map(line -> {
            try {
                return createCreditLimitRecordForEachLine(line);
            } catch (Exception ex) {
                log.info("Something went wrong while parsing line - %d in file - %d", line, file.getName());
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());

        creditLimitRecords.forEach(r -> r.setSourceWorkbook(file.getName()));

        return creditLimitRecords;
    }

    abstract CreditLimitRecord createCreditLimitRecordForEachLine(String line);
}
