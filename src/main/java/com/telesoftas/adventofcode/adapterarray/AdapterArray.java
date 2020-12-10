package com.telesoftas.adventofcode.adapterarray;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

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

    static List<Long> toNumbersList(InputStream input) {
        return new BufferedReader(new InputStreamReader(input))
            .lines()
            .map(Long::valueOf)
            .collect(toList());
    }
}
