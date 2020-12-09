package com.telesoftas.adventofcode.encodingerror;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@UtilityClass
public class EncodingError {

    static List<Long> toNumbersList(InputStream input) {
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
