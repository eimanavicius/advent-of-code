package com.telesoftas.adventofcode.rambunctiousrecitation;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day15RambunctiousRecitationTest {

    private static Stream<Arguments> providePart1Samples() {
        return Stream.of(
            Arguments.of(436, new int[]{0, 3, 6}),
            Arguments.of(1, new int[]{1, 3, 2}),
            Arguments.of(10, new int[]{2, 1, 3}),
            Arguments.of(27, new int[]{1, 2, 3}),
            Arguments.of(78, new int[]{2, 3, 1}),
            Arguments.of(438, new int[]{3, 2, 1}),
            Arguments.of(1836, new int[]{3, 1, 2})
        );
    }

    private static Stream<Arguments> providePart2Samples() {
        return Stream.of(
            Arguments.of(175594, new int[]{0, 3, 6}),
            Arguments.of(2578, new int[]{1, 3, 2}),
            Arguments.of(3544142, new int[]{2, 1, 3}),
            Arguments.of(261214, new int[]{1, 2, 3}),
            Arguments.of(6895259, new int[]{2, 3, 1}),
            Arguments.of(18, new int[]{3, 2, 1}),
            Arguments.of(362, new int[]{3, 1, 2})
        );
    }

    @Test
    void part1_sample() {
        final int[] seed = new int[]{0, 3, 6};

        final int lastNumber = Day15.lastNumberSpokenAfter(2020, seed);

        assertEquals(436, lastNumber);
    }

    @Test
    void part2_sample() {
        final int[] seed = new int[]{0, 3, 6};

        final int lastNumber = Day15.lastNumberSpokenAfter(30_000_000, seed);

        assertEquals(175594, lastNumber);
    }

    @ParameterizedTest
    @MethodSource("providePart1Samples")
    @Disabled
    void part1_2020_turns(int answer, int... seed) {
        final int lastNumber = Day15.lastNumberSpokenAfter(2020, seed);

        assertEquals(answer, lastNumber);
    }

    @ParameterizedTest
    @MethodSource("providePart2Samples")
    @Disabled
    void part2_30kk_turns(int answer, int... seed) {
        final int lastNumber = Day15.lastNumberSpokenAfter(30_000_000, seed);

        assertEquals(answer, lastNumber);
    }
}
