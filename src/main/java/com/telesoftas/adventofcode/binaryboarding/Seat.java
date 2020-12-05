package com.telesoftas.adventofcode.binaryboarding;

import lombok.Value;

@Value
public class Seat {

    int id;

    public int distanceTo(Seat seat) {
        return Math.abs(id - seat.id);
    }

    public Seat findBetween(Seat next) {
        return new Seat(Math.min(id, next.id) + 1);
    }
}
