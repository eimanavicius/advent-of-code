package com.telesoftas.adventofcode.customcustoms;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toSet;

@Log4j2
public class Day6 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day6.txt")) {

            long sum = toDeclarationsAsStringStream(requireNonNull(input))
                .map(Day6::stringToYesStream)
                .mapToLong(Set::size)
                .sum();

            log.info("Answer: {}", sum);

        }
    }

    public static Set<Integer> stringToYesStream(String groupDeclaration) {
        return stream(groupDeclaration.split("\\s"))
            .map(person -> person.chars().boxed().collect(toSet()))
            .reduce((everyoneYeses, next) -> {
                HashSet<Integer> result = new HashSet<>(everyoneYeses);
                result.retainAll(next);
                return result;
            })
            .orElse(Set.of());
    }

    public static Stream<String> toDeclarationsAsStringStream(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter("\n\n")
            .tokens();
    }
}
