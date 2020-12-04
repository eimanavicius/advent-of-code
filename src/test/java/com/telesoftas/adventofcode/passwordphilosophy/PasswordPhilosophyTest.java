package com.telesoftas.adventofcode.passwordphilosophy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordPhilosophyTest {

    @Test
    void build_SelfValidatingPassword_from_string() {
        var maybePassword = PasswordPhilosophy.stringToSelfValidatingPassword("1-3 a: abcde");

        assertTrue(maybePassword.isPresent());
        maybePassword.ifPresent(
            password -> assertEquals(
                new SelfValidatingPassword(
                    new Password("abcde".getBytes()),
                    new CharCountPredicate('a', 1, 3)
                ),
                password
            )
        );
    }

    @ParameterizedTest
    @CsvSource({"1,3,a,abcde", "2,3,a,aabcde"})
    void valid_password(int min, int max, char letter, String passwordValue) {
        SelfValidatingPassword password = new SelfValidatingPassword(
            new Password(passwordValue.getBytes()),
            new CharCountPredicate(letter, min, max)
        );

        assertTrue(password.isValid());
        assertTrue(password.isValidThrow());
        assertTrue(password.isValidAtomic());
    }

    @ParameterizedTest
    @CsvSource({"1,3,z,abcde", "2,3,a,abcde", "1,1,a,aabcde"})
    void invalid_password(int min, int max, char letter, String passwordValue) {
        SelfValidatingPassword password = new SelfValidatingPassword(
            new Password(passwordValue.getBytes()),
            new CharCountPredicate(letter, min, max)
        );

        assertFalse(password.isValid());
        assertFalse(password.isValidThrow());
        assertFalse(password.isValidAtomic());
    }


    @Test
    void collects_numbers_from_input_stream_to_integers_stream() {
        InputStream input = new ByteArrayInputStream("1,3,a,abcde\n1,3,z,abcde".getBytes());

        var list = PasswordPhilosophy.toPasswordWithPolicyAsStringStream(input).collect(toList());

        assertEquals(List.of("1,3,a,abcde", "1,3,z,abcde"), list);
    }
}
