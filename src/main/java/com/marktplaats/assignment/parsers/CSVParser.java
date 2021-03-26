package com.marktplaats.assignment.parsers;

import com.marktplaats.assignment.model.Birthday;
import com.marktplaats.assignment.model.CreditLimit;
import com.marktplaats.assignment.model.CreditLimitRecord;
import com.marktplaats.assignment.model.Name;

public class CSVParser extends AbstractFileParser {

    CreditLimitRecord createCreditLimitRecordForEachLine(String line) {
        String[] attr = line.split(",");
        return new CreditLimitRecord(new Name(attr[0] + "," + attr[1]), attr[2], attr[3], attr[4],
                new CreditLimit(attr[5], CreditLimit.CurrencyUnit.EURO), Birthday.createBirthdayFromString(attr[6], Birthday.Format.SLASH_SEPARATED));

    }
}
