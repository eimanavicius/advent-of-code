package com.telesoftas.adventofcode.rambunctiousrecitation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15RambunctiousRecitationTest {

    @Test
    void sample() {
        final int[] seed = new int[]{0, 3, 6};

        final int lastNumber = Day15.lastNumberSpokenAfter(2020, seed);

        assertEquals(436, lastNumber);
    }
}
