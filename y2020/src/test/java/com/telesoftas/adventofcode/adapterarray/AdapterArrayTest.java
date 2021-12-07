package com.telesoftas.adventofcode.adapterarray;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
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

    @Test
    void part2_sample1() {
        final int difference = AdapterArray.findAdapterArrangementCombinations(SAMPLE1);

        assertEquals(8, difference);
    }

    @Test
    void part2_sample2() {
        final int difference = AdapterArray.findAdapterArrangementCombinations(SAMPLE2);

        assertEquals(19208, difference);
    }

    @Test
    void part2_sample1_working_false_positive_solution() {
        final int difference = FalsePositive.findCombinations(SAMPLE1);

        assertEquals(8, difference);
    }

    static class FalsePositive {

        static int findCombinations(List<Long> numbers) {
            List<Long> jolts = new ArrayList<>();
            jolts.add(0L);
            jolts.addAll(numbers);
            jolts.add(jolts.get(jolts.size() - 1) + 3);
            final List<Long> sorted = jolts.stream().sorted(Long::compareTo).collect(toList());

            int comb = 0;

            for (int i = comb; i < sorted.size() - 1; i++) {
                long one = sorted.get(i);
                for (int j = i + 2; j < sorted.size(); j++) {
                    Long two = sorted.get(j);
                    if (two - one < 4) {
                        comb++;
                    }
                }
            }

            return comb;
        }
    }
}
