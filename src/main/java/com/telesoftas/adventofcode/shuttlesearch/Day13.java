package com.telesoftas.adventofcode.shuttlesearch;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day13 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day13.txt")) {
            final ShuttleSearch shuttle = toShuttleSearch(requireNonNull(input));

            log.info("Answer: {}", shuttle.busMultipliedByWaitMinutes());

        }
    }

    public static ShuttleSearch toShuttleSearch(InputStream input) {
        final Scanner scanner = new Scanner(input);
        final int departure = scanner.nextInt();
        final List<Integer> busses = scanner
            .useDelimiter("[,\\n]")
            .tokens()
            .filter(s -> !"x".equals(s))
            .map(Integer::valueOf)
            .collect(toList());
        return new ShuttleSearch(departure, busses);
    }
}
