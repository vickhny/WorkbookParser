package com.marktplaats.assignment.parsers;

import com.marktplaats.assignment.model.Birthday;
import com.marktplaats.assignment.model.CreditLimit;
import com.marktplaats.assignment.model.CreditLimitRecord;
import com.marktplaats.assignment.model.Name;

public class PRNParser extends AbstractFileParser {

    CreditLimitRecord createCreditLimitRecordForEachLine(String line) {

        String name = line.substring(0, 16).trim();
        String address = line.substring(16, 38).trim();
        String postcode = line.substring(38, 47).trim();
        String phone = line.substring(47, 61).trim();
        CreditLimit creditLimit = new CreditLimit(line.substring(61, 74).trim(), CreditLimit.CurrencyUnit.CENT);
        Birthday birthday = Birthday.createBirthdayFromString(line.substring(74).trim(), Birthday.Format.NO_SEPARATOR);
        return new CreditLimitRecord(new Name(name), address, postcode, phone, creditLimit, birthday);
    }
}
