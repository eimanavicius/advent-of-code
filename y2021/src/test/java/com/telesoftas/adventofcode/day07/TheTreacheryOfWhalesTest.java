package com.telesoftas.adventofcode.day07;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.telesoftas.adventofcode.day07.TheTreacheryOfWhales.progressiveFuelCost;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TheTreacheryOfWhalesTest {

    private static Stream<Arguments> positionsAndCosts() {
        return Stream.of(
            arguments("0 steps 0 fuel", 0, 5, 5),
            arguments("1 step 1 fuel", 1, 4, 5),
            arguments("2 steps 3 fuel", 3, 7, 5),
            arguments("3 steps 6 fuel", 6, 2, 5),
            arguments("4 steps 10 fuel", 10, 1, 5),
            arguments("5 steps 15 fuel", 15, 0, 5),
            arguments("9 steps 45 fuel", 45, 14, 5),
            arguments("11 steps 66 fuel", 66, 16, 5)
        );
    }

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

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("positionsAndCosts")
    void progressive_fuel_cost(String name, int cost, int from, int to) {
        assertEquals(cost, progressiveFuelCost(from, to));
    }
}
