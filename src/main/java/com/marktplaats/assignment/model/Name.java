package com.marktplaats.assignment.model;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(exclude = "hasSlash")
public class Name {

    String name;
    boolean hasSlash;

    public Name(String name) {
        if (name.startsWith("\"") && name.endsWith("\"")) {
            this.name = name.substring(1, name.length() - 1);
            hasSlash = true;
        } else {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        if (hasSlash)
            return "\"" + name + "\"";
        return name;
    }
}
