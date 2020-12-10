package com.telesoftas.adventofcode.adapterarray;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdapterArrayTest {

    public static final List<Long> SAMPLE1 = List.of(
        16L,
        10L,
        15L,
        5L,
        1L,
        11L,
        7L,
        19L,
        6L,
        12L,
        4L
    );

    public static final List<Long> SAMPLE2 = List.of(
        28L,
        33L,
        18L,
        42L,
        31L,
        14L,
        46L,
        20L,
        48L,
        47L,
        24L,
        23L,
        49L,
        45L,
        19L,
        38L,
        39L,
        11L,
        1L,
        32L,
        25L,
        35L,
        8L,
        17L,
        7L,
        9L,
        4L,
        2L,
        34L,
        10L,
        3L
    );

    @Test
    void name1_1() {
        final Long difference = AdapterArray.findJolt1and3DifferenceMultiplication(SAMPLE1);

        assertEquals(7 * 5, difference);
    }

    @Test
    void name1_2() {
        final Long difference = AdapterArray.findJolt1and3DifferenceMultiplication(SAMPLE2);

        assertEquals(22 * 10, difference);
    }
}
