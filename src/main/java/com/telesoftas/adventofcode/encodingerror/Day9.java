package com.telesoftas.adventofcode.encodingerror;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day9 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day9.txt")) {
            List<Long> numbers = toNumbersList(requireNonNull(input));

            // What is the first number that does not have this property?
            Long erroneous = findEncryptionError(numbers, 25);
            log.info("Answer: {}", erroneous);

            // What is the encryption weakness in your XMAS-encrypted list of numbers?
            Long weakness = findEncryptionWeakness(findEncryptionWeaknessRange(numbers, erroneous));
            log.info("Answer: {}", weakness);
        } catch (EncryptionErrorNotFoundException e) {
            log.error(e);
        }
    }

    private static List<Long> toNumbersList(InputStream input) {
        return new BufferedReader(new InputStreamReader(input))
            .lines()
            .map(Long::valueOf)
            .collect(toList());
    }

    public static Long findEncryptionError(List<Long> numbers, int preamble) throws EncryptionErrorNotFoundException {
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
                return next;
            }
        }
        throw new EncryptionErrorNotFoundException();
    }

    public static Set<Long> findEncryptionWeaknessRange(List<Long> sample, Long weakness) {
        for (int i = 0; i < sample.size(); i++) {
            long sum = sample.get(i);
            for (int j = i + 1; j < sample.size(); j++) {
                sum += sample.get(j);
                if (sum == weakness) {
                    return new HashSet<>(sample.subList(i, j + 1));
                }
                if (sum > weakness) {
                    break;
                }
            }
        }
        return Set.of();
    }

    public static Long findEncryptionWeakness(Set<Long> weaknessRange) {
        return weaknessRange.stream()
            .sorted()
            .collect(
                collectingAndThen(
                    toList(),
                    longs -> {
                        if (longs.isEmpty()) {
                            return null;
                        }
                        return longs.get(0) + longs.get(longs.size() - 1);
                    }
                )
            );
    }
}
