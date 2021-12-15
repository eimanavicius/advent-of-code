package com.telesoftas.adventofcode.day15;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChitonTest {

    @Test
    void sample_part_1() {
        String input = """
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
        List<List<Integer>> riskLevels = Chiton.toRiskLevels(new ByteArrayInputStream(input.getBytes()));

        int risk = Chiton.lowestTotalRisk(Chiton.toCave(riskLevels), "9,9");

        assertEquals(40, risk);
    }

    @Test
    void read_risk_levels_from_text() {
        List<List<Integer>> riskLevels = Chiton.toRiskLevels(new ByteArrayInputStream("123\n456\n789".getBytes()));

        assertEquals(List.of(List.of(1, 2, 3), List.of(4, 5, 6), List.of(7, 8, 9)), riskLevels);
    }
}
