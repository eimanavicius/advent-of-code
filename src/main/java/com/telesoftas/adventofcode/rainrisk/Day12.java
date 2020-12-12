package com.telesoftas.adventofcode.rainrisk;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;

@Log4j2
public class Day12 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day12.txt")) {
            final Position cur = new Position();
            toActionStream(requireNonNull(input))
                .forEach(cur::exec);

            log.info("Answer: {}", cur.manhattanDistance());
        }
    }

    public static Stream<Action> toActionStream(InputStream input) {
        return new Scanner(input)
            .tokens()
            .map(action -> new Action(action.charAt(0), parseInt(action.substring(1))));
    }
}
