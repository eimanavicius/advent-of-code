package com.telesoftas.adventofcode.passwordphilosophy;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day2 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day2.txt")) {
            long count = PasswordPhilosophy.toPasswordWithPolicyAsStringStream(requireNonNull(input))
                .map(line -> PasswordPhilosophy.stringToSelfValidatingPassword(line, ExactlyOnePositionPredicate::new))
                .flatMap(Optional::stream)
                .filter(SelfValidatingPassword::isValid)
                .count();

            log.info("Answer: {}", count);
        }
    }
}
