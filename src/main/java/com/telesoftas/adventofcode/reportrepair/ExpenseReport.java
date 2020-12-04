package com.telesoftas.adventofcode.reportrepair;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@UtilityClass
public class ExpenseReport {

    public static int find2(List<Integer> expenses) throws NothingSumsTo2020Exception {
        int size = expenses.size();
        for (int i = 0; i < size - 1; i++) {
            Integer first = expenses.get(i);
            for (Integer second : expenses.subList(i + 1, size)) {
                if (first + second == 2020) {
                    return first * second;
                }
            }
        }
        throw new NothingSumsTo2020Exception();
    }

    public static Stream<Integer> toIntegersStream(InputStream input) {
        return new Scanner(input)
            .tokens()
            .map(Integer::valueOf);
    }
}
