package com.telesoftas.adventofcode.passportprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImprovedPassportValidationTest {


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
    private PassportValidator validator;

    @BeforeEach
    void setUp() {
        validator = new PassportValidator();
    }


    @Test
    void valid_passport_sample_with_optional_fields() {
        Passport passport = PassportFixture.make();

        assertTrue(validator.isValid(passport));
    }

    @Test
    void valid_passport_sample_without_optional_fields() {
        Passport passport = PassportFixture.makeWithoutFields(OPTIONAL);

        assertTrue(validator.isValid(passport));
    }

    @Test
    void invalid_passport_sample_without_req_fields() {
        Passport passport = PassportFixture.makeWithoutFields(REQUIRED);

        assertFalse(validator.isValid(passport));
    }

    @ParameterizedTest
    @CsvSource({
        "byr,2000",
        "iyr,2010",
        "eyr,2020",
        "hgt,150cm",
        "hgt,193cm",
        "hgt,59in",
        "hgt,76in",
        "hcl,#aabbcc",
        "ecl,amb",
        "pid,012345678",
        "pid,000000001",
    })
    void valid(String key, String value) {
        Passport passport = PassportFixture.makeWithField(key, value);

        assertTrue(validator.isValid(passport));
    }

    @ParameterizedTest
    @CsvSource({
        "byr,1410",
        "iyr,1410",
        "eyr,1410",
        "hgt,149cm",
        "hgt,194cm",
        "hgt,58in",
        "hgt,77in",
        "hcl,#aaa",
        "ecl,blue",
        "pid,123456",
        "pid,0123456789",
        "pid,#b6652a",
    })
    void invalid(String key, String value) {
        Passport passport = PassportFixture.makeWithField(key, value);

        assertFalse(validator.isValid(passport));
    }
}
