package com.telesoftas.adventofcode.binaryboarding;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Comparator.comparingInt;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day5 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day5.txt")) {
            readBoardingPasses(requireNonNull(input))
                .map(BoardingPass::findSeat)
                .sorted(comparingInt(Seat::getId))
                .collect(collectingAndThen(toList(), Day5::findMissingSeat))
                .ifPresentOrElse(
                    seat -> log.info("Answer: {}", seat.getId()),
                    () -> log.error("All seats taken")
                );
        }
    }

    public static Optional<Seat> findMissingSeat(List<Seat> sorted) {
        for (int i = 0; i < sorted.size() - 1; i++) {
            Seat seat = sorted.get(i);
            Seat next = sorted.get(i + 1);
            if (seat.distanceTo(next) > 1) {
                return of(seat.findBetween(next));
            }
        }
        return empty();
    }

    private static Stream<BoardingPass> readBoardingPasses(InputStream input) {
        return readBoardingPassesAsString(input)
            .map(String::getBytes)
            .map(BoardingPass::new);
    }

    private static Stream<String> readBoardingPassesAsString(InputStream input) {
        return new Scanner(input)
            .useDelimiter("\n")
            .tokens();
    }
}
