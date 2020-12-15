package com.telesoftas.adventofcode.rambunctiousrecitation;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class Day15 {

    public static void main(String[] args) throws IOException {
        final int[] seed = {12, 20, 0, 6, 1, 17, 7};

        log.info("Answer: {}", lastNumberSpokenAfter(2020, seed));
        log.info("Answer: {}", lastNumberSpokenAfter(30_000_000, seed));
    }

    public static int lastNumberSpokenAfter(int turns, int... seed) {
        Map<Integer, Integer> repetitions = new HashMap<>();

        for (int i = 1; i < seed.length; i++) {
            repetitions.put(seed[i - 1], i);
        }

        int lastNumber = seed[seed.length - 1];

        for (int turn = seed.length; turn < turns; turn++) {
            if (repetitions.containsKey(lastNumber)) {
                final int spokenTurn = repetitions.get(lastNumber);
                repetitions.put(lastNumber, turn);
                lastNumber = turn - spokenTurn;
            } else {
                repetitions.put(lastNumber, turn);
                lastNumber = 0;
            }
        }

        return lastNumber;
    }
}
