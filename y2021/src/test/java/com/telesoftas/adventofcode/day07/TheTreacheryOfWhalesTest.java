package com.telesoftas.adventofcode.day07;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TheTreacheryOfWhalesTest {

    @Test
    void sample() {
        int amount = TheTreacheryOfWhales.findFuelRequiredToAlignPositions(16, 1, 2, 0, 4, 2, 7, 1, 2, 14);

        assertEquals(37, amount);
    }
}
