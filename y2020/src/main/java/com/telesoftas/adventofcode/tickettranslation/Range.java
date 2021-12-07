package com.telesoftas.adventofcode.tickettranslation;

import static java.lang.Integer.parseInt;

public record Range(int min, int max) {

    public static Range valueOf(String range) {
        final String[] parts = range.split("-");
        return new Range(parseInt(parts[0]), parseInt(parts[1]));
    }

    public boolean inRangeInclusive(int value) {
        return min <= value && value <= max;
    }
}
