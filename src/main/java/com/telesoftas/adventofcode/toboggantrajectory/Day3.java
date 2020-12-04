package com.telesoftas.adventofcode.toboggantrajectory;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

@Log4j2
public class Day3 {

    public static void main(String[] args) throws IOException {
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("day3.txt")) {
            byte[] map = stream.readAllBytes();

            int trees = countEncounteredTrees(map);

            log.info("Answer: {}", trees);
        }
    }

    static int countEncounteredTrees(byte[] map) {
        int width = widthOfMap(map);
        int height = heightOfMap(map, width);

        int trees = 0;
        for (int x = 3, y = 1; y < height; x = (x + 3) % width, y += 1) {
            int nextPosition = y * (width + 1) + x;
            if ('#' == map[nextPosition]) {
                trees++;
            }
        }

        return trees;
    }

    private static int heightOfMap(byte[] map, int width) {
        for (int i = map.length - 1; i >= 0; i--) {
            if (map[i] == '\n') {
                return (i / (width + 1)) + 1;
            }
        }
        throw new RuntimeException();
    }

    private static int widthOfMap(byte[] map) {
        for (int i = 0; i < map.length; i++) {
            if (map[i] == '\n') {
                return i;
            }
        }
        throw new RuntimeException();
    }
}
