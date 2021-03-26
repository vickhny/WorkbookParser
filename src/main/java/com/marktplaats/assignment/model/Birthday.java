package com.marktplaats.assignment.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Birthday {

    static DateTimeFormatter SLASH_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static DateTimeFormatter NO_SLASH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private final LocalDate date;
    private final DateTimeFormatter formatter;

    private Birthday(String date, DateTimeFormatter formatter) {
        this.formatter = formatter;
        this.date = LocalDate.parse(date, formatter);
    }

    public static Birthday createBirthdayFromString(String date, Format format) {
        switch (format) {
            case SLASH_SEPARATED:
                return new Birthday(date, SLASH_FORMATTER);
            case NO_SEPARATOR:
                return new Birthday(date, NO_SLASH_FORMATTER);
            default:
                throw new IllegalStateException("Invalid date format - " + format);
        }
    }

    public String date() {
        return date.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Birthday birthday = (Birthday) o;
        return Objects.equals(date, birthday.date) &&
                Objects.equals(formatter, birthday.formatter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, formatter);
    }

    @Override
    public String toString() {
        return "Birthday{" +
                "date=" + date +
                '}';
    }

    public enum Format {
        SLASH_SEPARATED,
        NO_SEPARATOR
    }
}
