package com.telesoftas.adventofcode.shuttlesearch;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShuttleSearchTest {

    @Test
    void name() {
        final ShuttleSearch shuttle = new ShuttleSearch(939, List.of(7, 13, 59, 31, 19));

        assertEquals(295, shuttle.busMultipliedByWaitMinutes());
    }
}
