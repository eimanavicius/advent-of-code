package com.telesoftas.adventofcode.seatingsystem;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Log4j2
class SeatingSystemImmediatelyTest {

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
        + "#.LL.L#.##\n"
        + "#LLLLLL.L#\n"
        + "L.L.L..L..\n"
        + "#LLL.LL.L#\n"
        + "#.LL.LL.LL\n"
        + "#.LLLL#.##\n"
        + "..L.L.....\n"
        + "#LLLLLLLL#\n"
        + "#.LLLLLL.L\n"
        + "#.#LLLL.##\n").getBytes();

    @Test
    void step1() {
        SeatingSystem system = new SeatingSystem(STEP1);

        system.modelChangeByImmediateAdjacency();

        assertArrayEquals(STEP2, system.getLayout());
        assertEquals(71, system.countOccupiedSeats());
    }

    @Test
    void step2() {
        SeatingSystem system = new SeatingSystem(STEP2);

        system.modelChangeByImmediateAdjacency();

        assertArrayEquals(STEP3, system.getLayout());
        assertEquals(20, system.countOccupiedSeats());
    }

    @Test
    void step3() {
        SeatingSystem system = new SeatingSystem(STEP3);

        system.modelChangeByImmediateAdjacency();

        assertEquals(51, system.countOccupiedSeats());
    }

    @Test
    void done() {
        byte[] layout = ("#.#L.L#.##\n"
            + "#LLL#LL.L#\n"
            + "L.#.L..#..\n"
            + "#L##.##.L#\n"
            + "#.#L.LL.LL\n"
            + "#.#L#L#.##\n"
            + "..L.L.....\n"
            + "#L#L##L#L#\n"
            + "#.LLLLLL.L\n"
            + "#.#L#L#.##\n").getBytes();
        SeatingSystem system = new SeatingSystem(layout);

        final int occupiedSeats = system.modelByOccupiedImmediatelyAdjacentSeats();

        assertArrayEquals(layout, system.getLayout());
        assertEquals(37, occupiedSeats);
    }
}
