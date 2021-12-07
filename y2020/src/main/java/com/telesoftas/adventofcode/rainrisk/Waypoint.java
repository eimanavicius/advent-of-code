package com.telesoftas.adventofcode.rainrisk;

public class Waypoint {

    Point point;

    public Waypoint(Point point) {
        this.point = point;
    }

    public void turnLeft(int degree) {
        for (int i = 0; i < degree; i += 90) {
            point = point.left();
        }
    }

    public void turnRight(int degree) {
        for (int i = 0; i < degree; i += 90) {
            point = point.right();
        }
    }

    public void moveEast(int distance) {
        point = point.withX(point.getX() + distance);
    }

    public void moveWest(int distance) {
        point = point.withX(point.getX() - distance);
    }

    public void moveSouth(int distance) {
        point = point.withY(point.getY() - distance);
    }

    public void moveNorth(int distance) {
        point = point.withY(point.getY() + distance);
    }

    public int getX() {
        return point.getX();
    }

    public int getY() {
        return point.getY();
    }
}
