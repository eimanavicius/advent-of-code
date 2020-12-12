package com.telesoftas.adventofcode.rainrisk;

public class Position {

    private static final String[] FACES = {"east", "south", "west", "north"};

    private int face = 0;

    private int east = 0;

    private int south = 0;

    private int west = 0;

    private int north = 0;

    public void exec(Action action) {
        switch (action.getDirection()) {
            case 'N':
                goNorth(action.getDistance());
                break;
            case 'S':
                goSouth(action.getDistance());
                break;
            case 'E':
                goEast(action.getDistance());
                break;
            case 'W':
                goWest(action.getDistance());
                break;
            case 'L':
                turnLeft(action.getDistance());
                break;
            case 'R':
                turnRight(action.getDistance());
                break;
            case 'F':
                goForward(action.getDistance());
                break;
            default:
        }
    }

    private void turnLeft(int distance) {
        for (int i = 0; i < distance; i += 90) {
            if (face == 0) {
                face = FACES.length - 1;
            } else {
                face = face - 1;
            }
        }
    }

    private void turnRight(int distance) {
        for (int i = 0; i < distance; i += 90) {
            face = (face + 1) % FACES.length;
        }
    }

    private void goEast(int distance) {
        east += distance;
    }

    private void goNorth(int distance) {
        north += distance;
    }

    private void goSouth(int distance) {
        south += distance;
    }

    private void goWest(int distance) {
        west += distance;
    }

    public int manhattanDistance() {
        return Math.abs(west - east) + Math.abs(north - south);
    }

    private void goForward(int distance) {
        if ("east".equals(FACES[face])) {
            goEast(distance);
        } else if ("north".equals(FACES[face])) {
            goNorth(distance);
        } else if ("west".equals(FACES[face])) {
            goWest(distance);
        } else if ("south".equals(FACES[face])) {
            goSouth(distance);
        }
    }
}
