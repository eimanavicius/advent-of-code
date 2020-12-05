package com.telesoftas.adventofcode.binaryboarding;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day5 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day5.txt")) {
            List<Integer> sorted = new Scanner(requireNonNull(input))
                .useDelimiter("\n")
                .tokens()
                .map(Day5::binarySpacePartitioning)
                .sorted(Integer::compareTo)
                .collect(toList());

            for (int i = 0; i < sorted.size() - 1; i++) {
                if (sorted.get(i) - sorted.get(i + 1) < -1) {
                    int missingSeatId = sorted.get(i + 1) - 1;
                    log.info("Answer: {}", missingSeatId);
                    return;
                }
            }

            log.error("All seats taken");
        }
    }

    private static int binarySpacePartitioning(String input) {
        byte[] bytes = input.getBytes();

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
        return lower * 8 + right;
    }
}
