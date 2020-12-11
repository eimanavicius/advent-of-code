package com.telesoftas.adventofcode.seatingsystem;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day11 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day11.txt")) {
            SeatingSystem system = new SeatingSystem(requireNonNull(input).readAllBytes());

            // How many seats end up occupied?
            int occupied = system.modelOccupiedSeats();
            log.info("Answer: {}", occupied);
        }
    }
}
