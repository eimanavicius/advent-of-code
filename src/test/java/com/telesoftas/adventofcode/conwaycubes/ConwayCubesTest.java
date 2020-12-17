package com.telesoftas.adventofcode.conwaycubes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConwayCubesTest {

    private ConwayCubes conwayCubes;

    @BeforeEach
    void setUp() {
        conwayCubes = new ConwayCubes(new byte[][][]{
            {
                {'.', '#', '.'},
                {'.', '.', '#'},
                {'#', '#', '#'},
            },
        });
    }

    @Test
    void after_one_cycle() {
        final ConwayCubes cycle1 = conwayCubes.executeBootUp();

        assertEquals(11, cycle1.activeCubes());
    }

    @Test
    void after_two_cycle() {
        assertEquals(21, conwayCubes.activeCubesAfterCycles(2));
    }

    @Test
    void after_three_cycle() {
        assertEquals(38, conwayCubes.activeCubesAfterCycles(3));
    }

    @Test
    void after_six_cycle() {
        assertEquals(112, conwayCubes.activeCubesAfterCycles(6));
    }
}
