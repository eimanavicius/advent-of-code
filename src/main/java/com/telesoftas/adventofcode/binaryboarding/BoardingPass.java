package com.telesoftas.adventofcode.binaryboarding;

import lombok.Value;

@Value
public class BoardingPass {

    byte[] directions;

    public Seat findSeat() {
        byte[] bytes = directions;

        int lower = 0, upper = 127;

        for (int i = 0; i < 7; i++) {
            switch (bytes[i]) {
                case 'F':
                    upper -= (1 + upper - lower) / 2;
                    break;
                case 'B':
                    lower += (1 + upper - lower) / 2;
                    break;
            }
        }

        int left = 0, right = 7;
        for (int i = 7; i < 10; i++) {
            switch (bytes[i]) {
                case 'R':
                    left -= (left - right - 1) / 2;
                    break;
                case 'L':
                    right += (left - right - 1) / 2;
                    break;
            }
        }

        return new Seat(lower * 8 + right);
    }
}
