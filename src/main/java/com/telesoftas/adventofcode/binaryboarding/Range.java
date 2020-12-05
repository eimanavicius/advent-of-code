package com.telesoftas.adventofcode.binaryboarding;

import lombok.Value;

@Value
class Range {

    int lower;
    int upper;

    public Range partition(byte[] directions) {
        Range range = this;
        for (byte direction : directions) {
            range = switch (direction) {
                case 'F', 'L' -> range.lowerHalf();
                case 'B', 'R' -> range.upperHalf();
                default -> throw new IllegalStateException("Unexpected direction: " + direction);
            };
        }
        return range;
    }

    public Range lowerHalf() {
        return new Range(lower, upper - half());
    }

    public Range upperHalf() {
        return new Range(lower + half(), upper);
    }

    private int half() {
        return (1 + upper - lower) / 2;
    }
}
