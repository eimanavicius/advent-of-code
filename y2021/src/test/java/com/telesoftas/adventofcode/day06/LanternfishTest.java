package com.telesoftas.adventofcode.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanternfishTest {

    @Test
    void sample_part_1() {
        int amount = Lanternfish.amountOfLanternFishAfter(80, new int[]{3, 4, 3, 1, 2});

        assertEquals(5934, amount);
    }

    @Test
    void single_fish_replication() {
        assertEquals(1, Lanternfish.singleFishReplication(0, 0));
        assertEquals(1, Lanternfish.singleFishReplication(1, 1));
        assertEquals(2, Lanternfish.singleFishReplication(0, 1));
        assertEquals(2, Lanternfish.singleFishReplication(0, 6));

        assertEquals(2, Lanternfish.singleFishReplication(3, 5));

        assertEquals(3, Lanternfish.singleFishReplication(0, 8));
        assertEquals(4, Lanternfish.singleFishReplication(0, 10));
    }
}
