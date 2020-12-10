package com.telesoftas.adventofcode.adapterarray;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day10 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day10.txt")) {
            List<Long> numbers = AdapterArray.toNumbersList(requireNonNull(input));

            // What is the number of 1-jolt differences multiplied by the number of 3-jolt differences?
            Long difference = AdapterArray.findJolt1and3DifferenceMultiplication(numbers);
            log.info("Answer: {}", difference);
        }
    }
}
