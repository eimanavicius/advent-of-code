package com.telesoftas.adventofcode.passportprocessing;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import static java.util.Optional.ofNullable;

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

    /**
     * byr (Birth Year) - four digits; at least 1920 and at most 2002.
     */
    @Min(1920)
    @Max(2002)
    @NotNull
    Integer birthYear;

    /**
     * iyr (Issue Year) - four digits; at least 2010 and at most 2020.
     */
    @Min(2010)
    @Max(2020)
    @NotNull
    Integer issueYear;

    /**
     * eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
     */
    @Min(2020)
    @Max(2030)
    @NotNull
    Integer expirationYear;

    /**
     * hgt (Height) - a number followed by either cm or in:
     * <li>If cm, the number must be at least 150 and at most 193.</li>
     * <li>If in, the number must be at least 59 and at most 76.</li>
     */
    @Min(150)
    @Max(193)
    @NotNull
    Integer heightInCm;

    /**
     * hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
     */
    @Pattern(regexp = "^#[0-9a-f]{6}$")
    @NotNull
    String hairColor;

    /**
     * ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
     */
    @Pattern(regexp = "^amb|blu|brn|gry|grn|hzl|oth$")
    @NotNull
    String eyeColor;

    /**
     * pid (Passport ID) - a nine-digit number, including leading zeroes.
     */
    @Pattern(regexp = "^[0-9]{9}$")
    @NotNull
    String passportId;

    /**
     * cid (Country ID) - ignored, missing or not.
     */
    String countryId;

    public Passport(Map<String, String> values) {
        fields = Map.copyOf(values);
        birthYear = ofNullable(values.get("byr")).map(Integer::parseInt).orElse(null);
        issueYear = ofNullable(values.get("iyr")).map(Integer::parseInt).orElse(null);
        expirationYear = ofNullable(values.get("eyr")).map(Integer::parseInt).orElse(null);
        heightInCm = Height.parseCentimeters(values.get("hgt")).orElse(null);
        hairColor = values.get("hcl");
        eyeColor = values.get("ecl");
        passportId = values.get("pid");
        countryId = values.get("cid");
    }

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
}
