package com.telesoftas.adventofcode.rainrisk;

public class Ship {

    private final Waypoint waypoint;

    Point point;

    public Ship(Point point, Waypoint waypoint) {
        this.point = point;
        this.waypoint = waypoint;
    }

    public int manhattanDistance() {
        return point.manhattanDistance(new Point(0, 0));
    }

    public void moveForward(int distance) {
        point = new Point(
            point.getX() + distance * waypoint.getX(),
            point.getY() + distance * waypoint.getY()
        );
    }

    public void exec(Action action) {
        switch (action.getDirection()) {
            case 'F' -> moveForward(action.getDistance());
            case 'N' -> waypoint.moveNorth(action.getDistance());
            case 'S' -> waypoint.moveSouth(action.getDistance());
            case 'E' -> waypoint.moveEast(action.getDistance());
            case 'W' -> waypoint.moveWest(action.getDistance());
            case 'L' -> waypoint.turnLeft(action.getDistance());
            case 'R' -> waypoint.turnRight(action.getDistance());
            default -> throw new IllegalStateException("Unexpected value: " + action.getDirection());
        }
    }
}
