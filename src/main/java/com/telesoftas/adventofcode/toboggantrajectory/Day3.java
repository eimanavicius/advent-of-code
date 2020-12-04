package com.telesoftas.adventofcode.toboggantrajectory;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Log4j2
public class Day3 {

    public static void main(String[] args) throws IOException {
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("day3.txt")) {
            TobogganMap map = new TobogganMap(stream.readAllBytes());

            long trees = map.multipliedTreesOnAllSlopes(List.of(
                new Slope(1, 1),
                new Slope(3, 1),
                new Slope(5, 1),
                new Slope(7, 1),
                new Slope(1, 2)
            ));

            log.info("Answer: {}", trees);
        }
    }
}
