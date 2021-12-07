package com.telesoftas.adventofcode.tickettranslation;

public record Rule(String name, Range... ranges) {

    public boolean matches(int value) {
        for (Range range : ranges) {
            if (range.inRangeInclusive(value)) {
                return true;
            }
        }
        return false;
    }
}
