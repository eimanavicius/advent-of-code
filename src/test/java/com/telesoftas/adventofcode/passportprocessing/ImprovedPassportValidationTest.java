package com.telesoftas.adventofcode.passportprocessing;

import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImprovedPassportValidationTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.byDefaultProvider().configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
        "byr,2000,birthYear",
        "iyr,2010,issueYear",
        "eyr,2020,expirationYear",
        "hgt,150cm,heightInCm",
        "hgt,193cm,heightInCm",
        "hgt,59in,heightInCm",
        "hgt,76in,heightInCm",
        "hcl,#aabbcc,hairColor",
        "ecl,amb,eyeColor",
        "pid,012345678,passportId",
        "pid,000000001,passportId",
    })
    void valid(String key, String value, String field) {
        Passport passport = new Passport(Map.of(key, value));

        assertTrue(validator.validateProperty(passport, field).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
        "byr,1410,birthYear",
        "iyr,1410,issueYear",
        "eyr,1410,expirationYear",
        "hgt,149cm,heightInCm",
        "hgt,194cm,heightInCm",
        "hgt,58in,heightInCm",
        "hgt,77in,heightInCm",
        "hcl,#aaa,hairColor",
        "ecl,blue,eyeColor",
        "pid,123456,passportId",
        "pid,0123456789,passportId",
        "pid,#b6652a,passportId",
    })
    void invalid(String key, String value, String field) {
        Passport passport = new Passport(Map.of(key, value));

        assertFalse(validator.validateProperty(passport, field).isEmpty());
    }
}
