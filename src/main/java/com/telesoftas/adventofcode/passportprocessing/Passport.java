package com.telesoftas.adventofcode.passportprocessing;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;

@Value
public class Passport {

    public static final String[] OPTIONAL = {
        // (Country ID)
        "cid",
    };

    public static final String[] REQUIRED = {
        // (Birth Year)
        "byr",
        // (Issue Year)
        "iyr",
        // (Expiration Year)
        "eyr",
        // (Height)
        "hgt",
        // (Hair Color)
        "hcl",
        // (Eye Color)
        "ecl",
        // (Passport ID)
        "pid",
    };

    Map<String, String> fields;

    public static Passport fromString(String passportAsString) {
        Map<String, String> passportFields = new HashMap<>();
        for (String field : passportAsString.split("\\s")) {
            String[] kv = field.split(":");
            passportFields.put(kv[0], kv[1]);
        }
        return new Passport(passportFields);
    }

    public String get(String key) {
        return fields.get(key);
    }

    public boolean isValid() {
        for (String key : REQUIRED) {
            if (!fields.containsKey(key)) {
                return false;
            }
        }
        return true;
    }
}
