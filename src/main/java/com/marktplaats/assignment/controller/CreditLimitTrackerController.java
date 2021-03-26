package com.marktplaats.assignment.controller;

import com.marktplaats.assignment.model.CreditLimitConflict;
import com.marktplaats.assignment.model.ProcessedRecord;
import com.marktplaats.assignment.service.CreditLimitTrackerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Controller
@Api(tags = "Credit Limit Tracker System", produces = "application/json")
public class CreditLimitTrackerController {

    CreditLimitTrackerService limitTrackerService;

    @Autowired
    public CreditLimitTrackerController(CreditLimitTrackerService limitTrackerService) {
        this.limitTrackerService = limitTrackerService;
    }

    @PostMapping(value = "convertToHtml")
    @ApiOperation(value = "Process credit limit and convert to html", notes = "API to read credit limit records from given files and return credit limits in html format.", response = ResponseEntity.class)
    public String convertToHtml(Model model, @RequestParam("file") MultipartFile[] multipartFiles) {

        List<ProcessedRecord> processedRecordList = limitTrackerService.convertToHtml(multipartFiles);
        model.addAttribute("processedRecordList", processedRecordList);
        return "records_and_conflicts";
    }

    @PostMapping(value = "findConflicts")
    @ApiOperation(value = "Process credit limit and convert to html", notes = "API to read credit limit records from given files, find credit limit conflicts and return response in html format.", response = ResponseEntity.class)
    public String findCreditConflicts(Model model, @RequestParam("file") MultipartFile[] multipartFiles) {

        List<ProcessedRecord> processedRecordList = limitTrackerService.convertToHtml(multipartFiles);
        List<CreditLimitConflict> creditLimitRecordsWithConflicts = limitTrackerService.findRecordsWithCreditConflict(processedRecordList);
        model.addAttribute("processedRecordList", processedRecordList);
        model.addAttribute("conflicts", creditLimitRecordsWithConflicts);
        return "records_and_conflicts";
    }

}
