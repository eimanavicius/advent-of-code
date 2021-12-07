package com.telesoftas.adventofcode.rainrisk;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RainRiskTest {

    @Test
    void step1() {
        final Ship ship = new Ship(new Point(0, 0), new Waypoint(new Point(10, 1)));

        ship.moveForward(10);

        assertEquals(new Point(100, 10), ship.point);
    }

    @Test
    void step2() {
        final Waypoint waypoint = new Waypoint(new Point(10, 1));

        waypoint.moveNorth(3);

        assertEquals(4, waypoint.point.getY());
    }

    @Test
    void step3() {
        final Ship ship = new Ship(new Point(100, 10), new Waypoint(new Point(10, 4)));

        ship.moveForward(7);

        assertEquals(new Point(170, 38), ship.point);
    }

    /**
     * R90 rotates the waypoint around the ship clockwise 90 degrees, <br/> moving it to 4 units east and 10 units south
     * of the ship.<br/> The ship remains at east 170, north 38.
     */
    @Test
    void step4() {
        final Waypoint waypoint = new Waypoint(new Point(10, 4));

        waypoint.turnRight(90);

        assertEquals(4, waypoint.point.getX());
        assertEquals(-10, waypoint.point.getY());
    }

    @Test
    void back_and_forth() {
        final Waypoint waypoint = new Waypoint(new Point(10, -2));

        waypoint.turnLeft(90);

        assertEquals(2, waypoint.point.getX());
        assertEquals(10, waypoint.point.getY());

        waypoint.turnRight(90);

        assertEquals(10, waypoint.point.getX());
        assertEquals(-2, waypoint.point.getY());
    }

    /**
     * F11 moves the ship to the waypoint 11 times (a total of 44 units east and 110 units south),<br/> leaving the ship
     * at east 214, south 72.<br/> The waypoint stays 4 units east and 10 units south of the ship.
     */
    @Test
    void step5() {
        final Waypoint waypoint = new Waypoint(new Point(4, -10));
        final Ship ship = new Ship(new Point(170, 38), waypoint);

        ship.moveForward(11);

        assertEquals(new Point(214, -72), ship.point);
    }

    @Test
    void sample() {
        final Ship ship = new Ship(new Point(0, 0), new Waypoint(new Point(10, 1)));

        List.of(
            new Action('F', 10),
            new Action('N', 3),
            new Action('F', 7),
            new Action('R', 90),
            new Action('F', 11)
        ).forEach(ship::exec);

        assertEquals(286, ship.manhattanDistance());
    }
}
