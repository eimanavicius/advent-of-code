package com.telesoftas.adventofcode.day07;

import org.junit.jupiter.api.Test;

import static com.telesoftas.adventofcode.day07.TheTreacheryOfWhales.progressiveFuelCost;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TheTreacheryOfWhalesTest {

    @Test
    void sample_part_1() {
        int amount = TheTreacheryOfWhales.findFuelRequiredToAlignPositions(16, 1, 2, 0, 4, 2, 7, 1, 2, 14);

        assertEquals(37, amount);
    }

    @Test
    void sample_part_2() {
        int amount = TheTreacheryOfWhales.findFuelRequiredToAlignPositionsWithProgressiveCost(
            16, 1, 2, 0, 4, 2, 7, 1, 2, 14
        );

        assertEquals(168, amount);
    }

    @Test
    void progressive_fuel_cost() {
        assertEquals(66, progressiveFuelCost(16, 5));
        assertEquals(10, progressiveFuelCost(1, 5));
        assertEquals(6, progressiveFuelCost(2, 5));
        assertEquals(15, progressiveFuelCost(0, 5));
        assertEquals(1, progressiveFuelCost(4, 5));
        assertEquals(3, progressiveFuelCost(7, 5));
        assertEquals(45, progressiveFuelCost(14, 5));
        assertEquals(0, progressiveFuelCost(5, 5));
    }
}
