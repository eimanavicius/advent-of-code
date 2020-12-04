package com.telesoftas.adventofcode.toboggantrajectory;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Log4j2
public class Day3 {

    public static void main(String[] args) throws IOException {
        try (InputStream stream = ClassLoader.getSystemResourceAsStream("day3.txt")) {
            byte[] map = stream.readAllBytes();

            long trees = multipliedTreesOnAllSlopes(map);

            log.info("Answer: {}", trees);
        }
    }

    private static long multipliedTreesOnAllSlopes(byte[] map) {
        List<Slope> slopes = List.of(
            new Slope(1, 1),
            new Slope(3, 1),
            new Slope(5, 1),
            new Slope(7, 1),
            new Slope(1, 2)
        );
        long trees = 1;
        for (Slope slope : slopes) {
            trees *= countEncounteredTrees(map, slope);
        }
        return trees;
    }

    static int countEncounteredTrees(byte[] map) {
        return countEncounteredTrees(map, 3, 1);
    }

    static int countEncounteredTrees(byte[] map, Slope slope) {
        return countEncounteredTrees(map, slope.getRight(), slope.getDown());
    }

    static int countEncounteredTrees(byte[] map, int right, int down) {
        int width = widthOfMap(map);
        int height = heightOfMap(map, width);

        int trees = 0;
        for (int x = right, y = down; y < height; x = (x + right) % width, y += down) {
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
