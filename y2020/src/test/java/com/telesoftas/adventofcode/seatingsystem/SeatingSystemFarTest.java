package com.telesoftas.adventofcode.seatingsystem;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class SeatingSystemFarTest {

    public static final byte[] STEP1 = (""
        + "L.LL.LL.LL\n"
        + "LLLLLLL.LL\n"
        + "L.L.L..L..\n"
        + "LLLL.LL.LL\n"
        + "L.LL.LL.LL\n"
        + "L.LLLLL.LL\n"
        + "..L.L.....\n"
        + "LLLLLLLLLL\n"
        + "L.LLLLLL.L\n"
        + "L.LLLLL.LL\n").getBytes();

    public static final byte[] STEP2 = (""
        + "#.##.##.##\n"
        + "#######.##\n"
        + "#.#.#..#..\n"
        + "####.##.##\n"
        + "#.##.##.##\n"
        + "#.#####.##\n"
        + "..#.#.....\n"
        + "##########\n"
        + "#.######.#\n"
        + "#.#####.##\n").getBytes();

    public static final byte[] STEP3 = (""
        + "#.LL.LL.L#\n"
        + "#LLLLLL.LL\n"
        + "L.L.L..L..\n"
        + "LLLL.LL.LL\n"
        + "L.LL.LL.LL\n"
        + "L.LLLLL.LL\n"
        + "..L.L.....\n"
        + "LLLLLLLLL#\n"
        + "#.LLLLLL.L\n"
        + "#.LLLLL.L#\n").getBytes();

    @Test
    void step1() {
        SeatingSystem system = new SeatingSystem(STEP1);

        system.modelChangeByFarAdjacency();

        assertArrayEquals(STEP2, system.getLayout());
        assertEquals(71, system.countOccupiedSeats());
    }

    @Test
    void step2() {
        SeatingSystem system = new SeatingSystem(STEP2);

        system.modelChangeByFarAdjacency();

        assertArrayEquals(STEP3, system.getLayout());
        assertEquals(7, system.countOccupiedSeats());
    }

    @Test
    void step3() {
        SeatingSystem system = new SeatingSystem(STEP3);

        system.modelChangeByFarAdjacency();

        assertEquals(53, system.countOccupiedSeats());
    }

    @Test
    void done() {
        byte[] layout = (""
            + "#.L#.L#.L#\n"
            + "#LLLLLL.LL\n"
            + "L.L.L..#..\n"
            + "##L#.#L.L#\n"
            + "L.L#.LL.L#\n"
            + "#.LLLL#.LL\n"
            + "..#.L.....\n"
            + "LLL###LLL#\n"
            + "#.LLLLL#.L\n"
            + "#.L#LL#.L#\n").getBytes();
        SeatingSystem system = new SeatingSystem(layout);

        final int occupiedSeats = system.modelOccupiedFarAdjacentSeats();

        assertArrayEquals(layout, system.getLayout());
        assertEquals(26, occupiedSeats);
    }
}
