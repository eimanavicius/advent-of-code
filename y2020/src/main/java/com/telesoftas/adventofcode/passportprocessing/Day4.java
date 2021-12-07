package com.telesoftas.adventofcode.passportprocessing;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day4 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day4.txt")) {
            PassportValidator validator = new PassportValidator();

            long count = PassportsBatch.toPassportStream(requireNonNull(input))
                .filter(validator::isValid)
                .count();

            log.info("Answer: {}", count);
        }
    }
}
