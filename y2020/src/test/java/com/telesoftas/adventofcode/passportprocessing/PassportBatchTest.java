package com.telesoftas.adventofcode.passportprocessing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PassportBatchTest {

    @ParameterizedTest
    @ValueSource(strings = {
        "key:coin",
        "key1:value1 key:coin",
        "key1:value1\nkey:coin"
    })
    void read_coin_passport_fields(String passport) {
        Map<String, String> fields = PassportsBatch.stringToMap(passport);

        assertEquals("coin", fields.get("key"));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "key:candy",
        "key1:value1 key:candy",
        "key1:value1\nkey:candy"
    })
    void read_candy_passport_fields(String passport) {
        Map<String, String> fields = PassportsBatch.stringToMap(passport);

        assertEquals("candy", fields.get("key"));
    }

    @Test
    void maps_fields_correctly() {
        Passport passport = PassportsBatch.mapToPassport(PassportFixture.VALID_VALUES);

        assertAll(
            () -> assertEquals(1937, passport.getBirthYear()),
            () -> assertEquals(2017, passport.getIssueYear()),
            () -> assertEquals(2020, passport.getExpirationYear()),
            () -> assertEquals(183, passport.getHeightInCm()),
            () -> assertEquals("#fffffd", passport.getHairColor()),
            () -> assertEquals("gry", passport.getEyeColor()),
            () -> assertEquals("860033327", passport.getPassportId()),
            () -> assertEquals("147", passport.getCountryId())
        );
    }

    @Test
    void split_batch() {
        InputStream batch = new ByteArrayInputStream(("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n"
            + "byr:1937 iyr:2017 cid:147 hgt:183cm\n"
            + "\n"
            + "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n"
            + "hcl:#cfa07d byr:1929").getBytes());

        List<String> list = PassportsBatch.toPassportAsStringStream(batch).collect(toList());

        assertEquals("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n"
            + "byr:1937 iyr:2017 cid:147 hgt:183cm", list.get(0));
        assertEquals("iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\n"
            + "hcl:#cfa07d byr:1929", list.get(1));
    }
}
