package com.telesoftas.adventofcode.crabcups;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CrabCupsTest {

    @Test
    void sample_after_10_moves() {
        final CrabCups crabCups = new CrabCups(List.of(3, 8, 9, 1, 2, 5, 4, 6, 7));

        assertEquals("92658374", crabCups.cupsAfter1AsString(10));
    }

    @Test
    void sample_after_100_moves() {
        final CrabCups crabCups = new CrabCups(List.of(3, 8, 9, 1, 2, 5, 4, 6, 7));

        assertEquals("67384529", crabCups.cupsAfter1AsString(100));
    }
}
