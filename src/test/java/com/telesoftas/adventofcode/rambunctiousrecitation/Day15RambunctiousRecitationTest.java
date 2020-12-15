package com.telesoftas.adventofcode.rambunctiousrecitation;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day15RambunctiousRecitationTest {

    @Test
    void sample() {
        final List<Integer> seed = List.of(0, 3, 6);

        final int lastNumber = Day15.lastNumberSpokenAfter2020Turns(seed);

        assertEquals(436, lastNumber);
    }
}
