package com.telesoftas.adventofcode.reportrepair;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@UtilityClass
public class ExpenseReport {

    public static int find3(List<Integer> expenses) throws NothingSumsTo2020Exception {
        int size = expenses.size();
        for (int i = 0; i < size - 1; i++) {
            try {
                return find2(expenses.subList(i + 1, size), expenses.get(i), expenses.get(i));
            } catch (NothingSumsTo2020Exception e) {
                // ignore
            }
        }
        throw new NothingSumsTo2020Exception();
    }

    public static int find2(List<Integer> expenses) throws NothingSumsTo2020Exception {
        return find2(expenses, 0, 1);
    }

    private static int find2(List<Integer> expenses, Integer sum, Integer multi) throws NothingSumsTo2020Exception {
        int size = expenses.size();
        for (int i = 0; i < size - 1; i++) {
            Integer first = expenses.get(i);
            for (Integer second : expenses.subList(i + 1, size)) {
                if (sum + first + second == 2020) {
                    return multi * first * second;
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
