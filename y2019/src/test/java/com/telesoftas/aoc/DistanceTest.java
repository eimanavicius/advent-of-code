package com.telesoftas.aoc;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DistanceTest {

    @Test
    void name() {
        Distance sut = new Distance();

        var wire = sut.calculateCoords("R2,U1,L1,D1");

        assertAll(
            () -> assertEquals(4, wire.size()),
            () -> assertEquals(Map.of(
                new Point(1, 0), 1,
                new Point(2, 0), 2,
                new Point(2, 1), 3,
                new Point(1, 1), 4
            ), wire)
        );
    }
}
