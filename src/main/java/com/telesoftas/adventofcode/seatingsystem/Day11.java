package com.telesoftas.adventofcode.seatingsystem;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Arrays.copyOf;
import static java.util.Objects.requireNonNull;

@Log4j2
public class Day11 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day11.txt")) {
            final byte[] layout = requireNonNull(input).readAllBytes();

            part1(copyOf(layout, layout.length));

            part2(copyOf(layout, layout.length));
        }
    }

    private static void part1(byte[] layout) {
        SeatingSystem system = new SeatingSystem(layout);

        // How many seats end up occupied?
        log.info("Answer: {}", system.modelByOccupiedImmediatelyAdjacentSeats());
    }

    private static void part2(byte[] layout) {
        SeatingSystem system = new SeatingSystem(layout);

        // Given the new visibility method and the rule change for occupied seats becoming empty,
        // how many seats end up occupied?
        log.info("Answer: {}", system.modelOccupiedFarAdjacentSeats());
    }
}
