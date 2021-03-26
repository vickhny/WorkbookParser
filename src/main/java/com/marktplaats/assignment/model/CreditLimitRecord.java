package com.marktplaats.assignment.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class CreditLimitRecord {

    private final Name name;

    private final String address;

    private final String postcode;

    private final String phone;

    private final CreditLimit creditLimit;

    private final Birthday birthday;

    private String sourceWorkbook;


    public CreditLimitRecord(Name name, String address, String postcode, String phone, CreditLimit creditLimit, Birthday birthday) {
        this.name = name;
        this.address = address;
        this.postcode = postcode;
        this.phone = phone;
        this.creditLimit = creditLimit;
        this.birthday = birthday;
    }

    public String getName() {
        return name.toString();
    }

    public String getBirthday() {
        return birthday.date();
    }

    public CreditLimit getCreditLimit() {
        return creditLimit;
    }

    public String getCreditLimitValue() {
        return creditLimit.formatCreditLimit();
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPhone() {
        return phone;
    }

    public String getSourceWorkbook() {
        return sourceWorkbook;
    }

    public void setSourceWorkbook(String sourceWorkbook) {
        this.sourceWorkbook = sourceWorkbook;
    }

    public boolean isTheSameRecord(CreditLimitRecord record) {
        if (this == record) {
            return true;
        }
        if (!name.equals(record.name)) {
            return false;
        }
        if (!address.equals(record.address)) {
            return false;
        }
        if (!postcode.equals(record.postcode)) {
            return false;
        }
        if (!phone.equals(record.phone)) {
            return false;
        }
        return birthday.toString().equals(record.birthday.toString());
    }

    public boolean hasEqualCreditLimit(CreditLimitRecord creditLimitRecord) {
        return creditLimit.equals(creditLimitRecord.creditLimit);
    }
}
