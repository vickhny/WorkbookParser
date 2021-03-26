package com.marktplaats.assignment.model;


import java.math.BigDecimal;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class CreditLimit {

    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    @Getter
    BigDecimal amount;

    @Getter
    CurrencyUnit currencyUnit;

    public CreditLimit(String amount, CurrencyUnit currencyUnit) {
        this(new BigDecimal(amount), currencyUnit);
    }

    public String formatCreditLimit() {
        return amount.toPlainString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CreditLimit that = (CreditLimit) o;

        if (currencyUnit != that.currencyUnit) {
            that = that.convertTo(currencyUnit);
        }
        return amount.equals(that.amount);
    }

    CreditLimit convertTo(CurrencyUnit currencyUnit) {
        switch (currencyUnit) {
            case CENT:
                BigDecimal amountInCents = amount.multiply(ONE_HUNDRED).setScale(0);
                return new CreditLimit(amountInCents, CurrencyUnit.CENT);
            case EURO:
                return new CreditLimit(amount.divide(ONE_HUNDRED), CurrencyUnit.EURO);
            default:
                throw new IllegalArgumentException();
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, currencyUnit);
    }

    @Override
    public String toString() {
        return "CreditLimit{" +
                "amount=" + amount +
                ", currencyUnit=" + currencyUnit +
                '}';
    }

    public enum CurrencyUnit {
        EURO,
        CENT
    }
}
