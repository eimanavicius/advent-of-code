package com.telesoftas.adventofcode.binaryboarding;

import lombok.Value;

@Value
public class Seat {

    int id;

    public Seat(int id) {
        this.id = id;
    }

    public Seat(int row, int column) {
        this(row * 8 + column);
    }

    public int distanceTo(Seat seat) {
        return Math.abs(id - seat.id);
    }

    public Seat findBetween(Seat next) {
        return new Seat(Math.min(id, next.id) + 1);
    }
}
