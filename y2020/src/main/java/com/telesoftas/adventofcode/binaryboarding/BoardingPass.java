package com.telesoftas.adventofcode.binaryboarding;

import lombok.Value;

import static java.util.Arrays.copyOfRange;

@Value
public class BoardingPass {

    byte[] directions;

    public Seat findSeat() {
        return new Seat(
            findRow(copyOfRange(directions, 0, 7)),
            findColumn(copyOfRange(directions, 7, 10))
        );
    }

    private int findRow(byte[] directions) {
        return new Range(0, 127)
            .partition(directions)
            .getLower();
    }

    private int findColumn(byte[] directions) {
        return new Range(0, 7)
            .partition(directions)
            .getUpper();
    }
}
