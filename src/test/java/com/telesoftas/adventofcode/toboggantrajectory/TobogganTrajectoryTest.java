package com.telesoftas.adventofcode.toboggantrajectory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TobogganTrajectoryTest {

    @Test
    void name() {
        byte[] map = (""
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
        ).getBytes();

        int trees = Day3.countEncounteredTrees(map);

        Assertions.assertEquals(7, trees);
    }

}
