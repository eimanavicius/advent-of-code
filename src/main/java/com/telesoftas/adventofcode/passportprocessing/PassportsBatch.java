package com.telesoftas.adventofcode.passportprocessing;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Optional.ofNullable;

@UtilityClass
public class PassportsBatch {

    public static Stream<String> toPassportAsStringStream(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter("\n\n")
            .tokens();
    }

    public static Stream<Passport> toPassportStream(InputStream batch) {
        return toPassportAsStringStream(batch)
            .map(PassportsBatch::stringToMap)
            .map(PassportsBatch::mapToPassport);
    }


    public static Map<String, String> stringToMap(String passportAsString) {
        Map<String, String> passportFields = new HashMap<>();
        for (String field : passportAsString.split("\\s")) {
            String[] kv = field.split(":");
            passportFields.put(kv[0], kv[1]);
        }
        return passportFields;
    }

    public static Passport mapToPassport(Map<String, String> values) {
        return new Passport(
            ofNullable(values.get("byr")).map(Integer::parseInt).orElse(null),
            ofNullable(values.get("iyr")).map(Integer::parseInt).orElse(null),
            ofNullable(values.get("eyr")).map(Integer::parseInt).orElse(null),
            Height.parseCentimeters(values.get("hgt")).orElse(null),
            values.get("hcl"),
            values.get("ecl"),
            values.get("pid"),
            values.get("cid")
        );
    }
}
