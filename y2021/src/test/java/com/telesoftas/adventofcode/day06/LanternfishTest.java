package com.telesoftas.adventofcode.day06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LanternfishTest {

    @Test
    void sample_part_1() {
        long amount = Lanternfish.amountOfLanternFishAfter(80, new int[]{3, 4, 3, 1, 2});

        assertEquals(5934, amount);
    }

    @Test
    void sample_part_2() {
        long amount = Lanternfish.amountOfLanternFishAfter(256, new int[]{3, 4, 3, 1, 2});

        assertEquals(26984457539L, amount);
    }

    @Test
    void single_fish_replication() {
        // original fish replicates
        assertEquals(1, singleFishReplication(1, 1));
        assertEquals(2, singleFishReplication(0, 1));
        assertEquals(2, singleFishReplication(0, 6));
        assertEquals(3, singleFishReplication(0, 9));

        // sample
        assertEquals(2, singleFishReplication(3, 5));

        // replicated fish replicates
        assertEquals(4, singleFishReplication(0, 10));
    }

    @Test
    void couple_fishes_replication() {
        assertEquals(6, Lanternfish.amountOfLanternFishAfter(9, new int[]{0, 0}));
        assertEquals(8, Lanternfish.amountOfLanternFishAfter(10, new int[]{0, 0}));
    }

    private long singleFishReplication(int fishTimer, int days) {
        return Lanternfish.amountOfLanternFishAfter(days, new int[]{fishTimer});
    }
}
