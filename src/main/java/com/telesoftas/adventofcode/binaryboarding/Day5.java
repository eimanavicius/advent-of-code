package com.telesoftas.adventofcode.binaryboarding;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

@Log4j2
public class Day5 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day5.txt")) {
            Optional<Integer> max = new Scanner(input)
                .useDelimiter("\n")
                .tokens()
                .map(Day5::binarySpacePartitioning)
                .max(Integer::compareTo);

            log.info("Answer: {}", max);
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
