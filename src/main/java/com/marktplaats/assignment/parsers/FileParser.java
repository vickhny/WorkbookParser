package com.marktplaats.assignment.parsers;

import com.marktplaats.assignment.model.ProcessedRecord;

import java.io.File;

public interface FileParser {

    ProcessedRecord parse(File file);
}
