package com.telesoftas.adventofcode.encodingerror;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day9 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day9.txt")) {
            List<Long> numbers = new BufferedReader(new InputStreamReader(requireNonNull(input)))
                .lines()
                .map(Long::valueOf)
                .collect(toList());

            final int preamble = 25;
            for (int i = preamble; i < numbers.size(); i++) {
                Long next = numbers.get(i);
                boolean valid = false;
                final int limit = Math.max(i - preamble, 0);
                for (int j = i - 1; j >= limit - 1; j--) {
                    for (int k = j - 1; k >= limit; k--) {
                        if (numbers.get(j) + numbers.get(k) == next) {
                            valid = true;
                            break;
                        }
                    }
                }
                if (!valid) {
                    log.info("Answer: {}", next);
                    break;
                }
            }
        }
    }
}
