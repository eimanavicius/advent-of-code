package com.telesoftas.adventofcode.conwaycubes;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day17 {

    public static void main(String[] args) {
        byte[][][] grid = new byte[][][]{
            new byte[][]{
                ".#.####.".getBytes(),
                ".#...##.".getBytes(),
                "..###.##".getBytes(),
                "#..#.#.#".getBytes(),
                "#..#....".getBytes(),
                "#.####..".getBytes(),
                "##.##..#".getBytes(),
                "#.#.#..#".getBytes()
            }
        };

        final ConwayCubes conwayCubes = new ConwayCubes(grid);

        log.info("Answer: {}", conwayCubes.activeCubesAfterCycles(6));
    }
}
