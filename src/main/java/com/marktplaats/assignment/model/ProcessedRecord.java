package com.marktplaats.assignment.model;

import com.marktplaats.assignment.common.FileType;

import java.util.List;
import java.util.Optional;

import lombok.Data;

@Data
public class ProcessedRecord {

    final String source;
    final List<CreditLimitRecord> records;
    final FileType sourceType;

    public Optional<CreditLimitRecord> findRecord(CreditLimitRecord record) {
        return records
                .stream()
                .filter(l -> l.isTheSameRecord(record))
                .findFirst();
    }

}
