package com.telesoftas.adventofcode.customcustoms;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day6 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day6.txt")) {

            long sum = toDeclarationsAsStringStream(requireNonNull(input))
                .map(Day6::stringToYesStream)
                .mapToLong(IntStream::count)
                .sum();

            log.info("Answer: {}", sum);

        }
    }

    public static IntStream stringToYesStream(String groupDeclaration) {
        return groupDeclaration.chars()
            .filter(symbol -> '\n' != symbol)
            .distinct();
    }

    public static Stream<String> toDeclarationsAsStringStream(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter("\n\n")
            .tokens();
    }
}
