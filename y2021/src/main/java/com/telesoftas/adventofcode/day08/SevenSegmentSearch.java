package com.telesoftas.adventofcode.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

public class SevenSegmentSearch {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day08.txt")) {
            var lines = toLines(input);

            int count = count1478(lines);

            System.out.println("In the output values, how many times do digits 1, 4, 7, or 8 appear?");
            System.out.println(count);
        }
    }

    public static int count1478(List<Line> input) {
        int sum = 0;
        Set<Integer> lengths = Set.of(7, 3, 4, 2);
        for (Line line : input) {
            for (String number : line.output()) {
                if (lengths.contains(number.length())) {
                    sum++;
                }
            }
        }
        return sum;
    }

    public static List<Line> toLines(InputStream input) {
        return new BufferedReader(new InputStreamReader(input))
            .lines()
            .map(line -> {
                var parts = line.split(" \\| ");
                return new Line(List.of(parts[0].split("\s")), List.of(parts[1].split("\s")));
            })
            .toList();
    }
}
