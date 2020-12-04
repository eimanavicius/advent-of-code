package com.telesoftas.adventofcode.passportprocessing;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PassportFixture {

    public Passport make() {
        return makeWithoutFields();
    }

    public Passport makeWithoutFields(String... fields) {
        Map<String, String> values = new HashMap<>(Map.of(
            "byr", "1937",
            "iyr", "2017",
            "eyr", "2020",
            "hgt", "183cm",
            "hcl", "#fffffd",
            "ecl", "gry",
            "pid", "860033327",
            "cid", "147"
        ));
        for (String field : fields) {
            values.remove(field);
        }
        return PassportsBatch.mapToPassport(values);
    }

    public static Passport makeWithField(String key, String value) {
        Map<String, String> values = new HashMap<>(Map.of(
            "byr", "1937",
            "iyr", "2017",
            "eyr", "2020",
            "hgt", "183cm",
            "hcl", "#fffffd",
            "ecl", "gry",
            "pid", "860033327",
            "cid", "147"
        ));

        values.put(key, value);

        return PassportsBatch.mapToPassport(values);
    }
}
