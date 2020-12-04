package com.telesoftas.adventofcode.passportprocessing;

import lombok.extern.log4j.Log4j2;

import java.io.InputStream;

@Log4j2
public class Day4 {

    public static void main(String[] args) {
        InputStream input = ClassLoader.getSystemResourceAsStream("day4.txt");

        long count = PassportsBatch.toPassportStream(input)
            .filter(Passport::isValid)
            .count();

        log.info("Answer: {}", count); //
    }
}
