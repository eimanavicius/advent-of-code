package com.telesoftas.adventofcode.day15;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChitonTest {

    public static final String SAMPLE = """
        1163751742
        1381373672
        2136511328
        3694931569
        7463417111
        1319128137
        1359912421
        3125421639
        1293138521
        2311944581""";

    @Test
    void sample_part_1() {
        List<List<Integer>> riskLevels = Chiton.toRiskLevels(new ByteArrayInputStream(SAMPLE.getBytes()));

        int risk = Chiton.lowestTotalRisk(Chiton.toCave(riskLevels), "9,9");

        assertEquals(40, risk);
    }

    @Test
    void sample_part_2() throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day15_sample_2.txt")) {
            List<List<Integer>> riskLevels = Chiton.toRiskLevels(input);

            int risk = Chiton.lowestTotalRisk(Chiton.toCave(riskLevels), "49,49");

            assertEquals(315, risk);
        }
    }

    @Test
    void expand_cave_x5() throws IOException {
        try (InputStream expected = ClassLoader.getSystemResourceAsStream("day15_sample_2.txt")) {
            List<List<Integer>> expectedCave = Chiton.toRiskLevels(expected);
            List<List<Integer>> riskLevels = Chiton.toRiskLevels(new ByteArrayInputStream(SAMPLE.getBytes()));

            List<List<Integer>> actualCave = Chiton.expandCave(riskLevels);

            assertEquals(expectedCave, actualCave);
        }
    }

    @Test
    void increment_on_edge_of_9() {
        List<List<Integer>> expectedCave = List.of(
            List.of(8, 9, 1),
            List.of(9, 1, 2),
            List.of(1, 2, 3)
        );
        List<List<Integer>> actualCave = Chiton.increment(List.of(
            List.of(7, 8, 9),
            List.of(8, 9, 1),
            List.of(9, 1, 2)
        ));

        assertEquals(expectedCave, actualCave);
    }

    @Test
    void read_risk_levels_from_text() {
        List<List<Integer>> riskLevels = Chiton.toRiskLevels(new ByteArrayInputStream("123\n456\n789".getBytes()));

        assertEquals(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9)), riskLevels);
    }
}
