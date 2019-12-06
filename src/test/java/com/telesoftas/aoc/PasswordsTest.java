package com.telesoftas.aoc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PasswordsTest {

    private Passwords pass;

    @BeforeEach
    void setUp() {
        pass = new Passwords();
    }

    @Test
    void verify_has_double_digits() {
        assertAll(
            () -> assertFalse(pass.hasDoubleDigits(123456)),
            () -> assertTrue(pass.hasDoubleDigits(113456))
        );
    }

    @Test
    void not_decreasing_digits() {
        assertTrue(pass.isIncreasingOrEqual(123456));
        assertFalse(pass.isIncreasingOrEqual(223450));
    }

    @Test
    void next_valid_password() {

        var next = pass.count();
        System.out.println(next);
    }
}
