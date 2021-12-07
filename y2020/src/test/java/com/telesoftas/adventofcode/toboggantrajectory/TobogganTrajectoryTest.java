package com.telesoftas.adventofcode.toboggantrajectory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TobogganTrajectoryTest {

    @Test
    void name() {
        TobogganMap map = new TobogganMap((""
            + "..##.......\n"
            + "#...#...#..\n"
            + ".#....#..#.\n"
            + "..#.#...#.#\n"
            + ".#...##..#.\n"
            + "..#.##.....\n"
            + ".#.#.#....#\n"
            + ".#........#\n"
            + "#.##...#...\n"
            + "#...##....#\n"
            + ".#..#...#.#\n"
        ).getBytes());

        int trees = map.countEncounteredTrees(new Slope(3, 1));

        Assertions.assertEquals(7, trees);
    }

}
