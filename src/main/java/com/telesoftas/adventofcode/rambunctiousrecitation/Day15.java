package com.telesoftas.adventofcode.rambunctiousrecitation;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class Day15 {

    public static void main(String[] args) throws IOException {
        int lastNumber = lastNumberSpokenAfter2020Turns(List.of(12, 20, 0, 6, 1, 17, 7));

        log.info("Answer: {}", lastNumber);
    }

    public static int lastNumberSpokenAfter2020Turns(List<Integer> seed) {
        Map<Integer, Integer> repetitions = new HashMap<>();

        for (int i = 1; i < seed.size(); i++) {
            repetitions.put(seed.get(i - 1), i);
        }
        int lastNumber = seed.get(seed.size() - 1);

        for (int turn = seed.size(); turn < 2020; turn++) {
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
