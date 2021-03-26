package com.marktplaats.assignment.model;

import lombok.Data;

@Data
public class CreditLimitConflict {

    final CreditLimitRecord creditLimitRecord;

    final String sourceWorkbook;

    final String creditLimit;
}
