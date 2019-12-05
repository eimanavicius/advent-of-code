package com.telesoftas.aoc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Set;

class DistanceTest {

    @Test
    void name() {
        Distance sut = new Distance();

        Set<Point> wire = sut.calculateCoords("R2,U1,L1,D1");

        assertAll(
            () -> assertEquals(4, wire.size()),
            () -> assertEquals(Set.of(
                new Point(1, 0),
                new Point(2, 0),
                new Point(2, 1),
                new Point(1, 1)
            ), wire)
        );
    }
}
