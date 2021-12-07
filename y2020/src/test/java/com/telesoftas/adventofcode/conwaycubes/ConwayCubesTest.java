package com.telesoftas.adventofcode.conwaycubes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConwayCubesTest {

    private ConwayCubes conwayCubes;

    @BeforeEach
    void setUp() {
        conwayCubes = new ConwayCubes(new byte[][]{
            {'.', '#', '.'},
            {'.', '.', '#'},
            {'#', '#', '#'},
        });
    }

    @Test
    void after_one_cycle() {
        final ConwayCubes cycle1 = conwayCubes.executeBootUp();

        assertEquals(29, cycle1.activeCubes());
    }

    @Test
    void after_two_cycle() {
        assertEquals(60, conwayCubes.activeCubesAfterCycles(2));
    }

    @Test
    void after_three_cycle() {
        assertEquals(320, conwayCubes.activeCubesAfterCycles(3));
    }

    @Test
    void after_six_cycle() {
        assertEquals(848, conwayCubes.activeCubesAfterCycles(6));
    }
}
