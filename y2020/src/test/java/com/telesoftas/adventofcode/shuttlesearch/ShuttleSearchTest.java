package com.telesoftas.adventofcode.shuttlesearch;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ShuttleSearchTest {

    @Test
    void name() {
        final ShuttleSearch shuttle = new ShuttleSearch(939, List.of(7, 13, 1, 1, 59, 1, 31, 19));

        assertEquals(295, shuttle.busMultipliedByWaitMinutes());
    }

    @Test
    void name2() {
        final ShuttleSearch shuttle = new ShuttleSearch(939, List.of(7, 13, 1, 1, 59, 1, 31, 19));

        assertEquals(1068781, shuttle.nextTimeMatchingOffset());
    }
}
