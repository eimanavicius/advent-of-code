package com.telesoftas.adventofcode.crabcombat;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class RecursiveCombatTest {

    @Test
    void order_compare() {
        assertNotEquals(List.of(2, 1), List.of(1, 2));
    }

    @Test
    void sample_game() {
        final RecursiveCombat combat = new RecursiveCombat(
            new Player("1", List.of(9, 2, 6, 3, 1)),
            new Player("2", List.of(5, 8, 4, 7, 10))
        );

        assertEquals(291, combat.winningPlayerScore());
    }

    @Test
    void sample_infinite_game() {
        final RecursiveCombat combat = new RecursiveCombat(
            new Player("1", List.of(43, 19)),
            new Player("2", List.of(2, 29, 14))
        );

        assertEquals(105, combat.winningPlayerScore());
    }
}
