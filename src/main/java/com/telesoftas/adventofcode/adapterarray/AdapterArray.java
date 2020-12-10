package com.telesoftas.adventofcode.adapterarray;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@UtilityClass
public class AdapterArray {

    static Long findJolt1and3DifferenceMultiplication(List<Long> numbers) {
        final List<Long> sorted = numbers.stream().sorted(Long::compareTo).collect(toList());

        long sum1 = sorted.get(0) == 1 ? 1 : 0;
        long sum3 = 1;
        for (int i = 0; i < sorted.size() - 1; i++) {
            long one = sorted.get(i);
            long two = sorted.get(i + 1);

            if (two - one == 1) {
                sum1++;
            } else if (two - one == 3) {
                sum3++;
            }
        }

        return sum1 * sum3;
    }

    static int findAdapterArrangementCombinations(List<Long> numbers) {
        final List<Long> adapters = numbers.stream().sorted(Long::compareTo).collect(toList());

        Map<Long, Integer> comb = new HashMap<>();
        comb.put(0L, 1);

        for (final Long adapter : adapters) {
            final Integer combsWithPrev = comb.getOrDefault(adapter - 3L, 0)
                + comb.getOrDefault(adapter - 2L, 0)
                + comb.getOrDefault(adapter - 1L, 0);
            comb.put(adapter, combsWithPrev);
        }

        return comb.getOrDefault(adapters.get(adapters.size() - 1), -1);
    }

    static List<Long> toNumbersList(InputStream input) {
        return new BufferedReader(new InputStreamReader(input))
            .lines()
            .map(Long::valueOf)
            .collect(toList());
    }
}
