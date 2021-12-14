package com.telesoftas.adventofcode.day08;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

public class SevenSegmentSearch {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day08.txt")) {
            var lines = toLines(input);

            int count = count1478(lines);

            System.out.println("In the output values, how many times do digits 1, 4, 7, or 8 appear?");
            System.out.println(count);

            int sum = sumOutputValues(lines);

            System.out.println("What do you get if you add up all of the output values?");
            System.out.println(sum);
        }
    }

    public static int sumOutputValues(List<Line> lines) {
        return lines.stream()
            .mapToInt(line -> {
                Map<Set<Character>, String> mapping = signalToNumberMap(line);
                String outputNumberAsString = line.streamOutputCharsets()
                    .map(mapping::get)
                    .collect(joining());
                return Integer.parseInt(outputNumberAsString);
            })
            .sum();
    }

    private static Map<Set<Character>, String> signalToNumberMap(Line line) {
        return numberToSignalMap(line.toUniqueSignals())
            .entrySet().stream().collect(toMap(Entry::getValue, entry -> entry.getKey().toString()));
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

    public static Map<Integer, Set<Character>> numberToSignalMap(Set<Set<Character>> signals) {
        var one = findUniqueSignal(signals, 2);
        var four = findUniqueSignal(signals, 4);
        var seven = findUniqueSignal(signals, 3);
        var eight = findUniqueSignal(signals, 7);

        var fiveSegments = signals.stream().filter(x -> x.size() == 5).collect(toSet());
        Set<Character> two = Set.of();
        Set<Character> three = Set.of();
        Set<Character> five = Set.of();
        for (Set<Character> x : fiveSegments) {
            if (x.containsAll(one)) {
                three = x;
            } else if (x.stream().filter(four::contains).count() == 2) {
                two = x;
            } else {
                five = x;
            }
        }

        var sixSegments = signals.stream().filter(x -> x.size() == 6).collect(toSet());
        Set<Character> zero = Set.of();
        Set<Character> six = Set.of();
        Set<Character> nine = Set.of();
        for (Set<Character> x : sixSegments) {
            if (x.containsAll(four)) {
                nine = x;
            } else if (x.containsAll(one)) {
                zero = x;
            } else {
                six = x;
            }
        }

        return Map.of(
            0, zero,
            1, one,
            2, two,
            3, three,
            4, four,
            5, five,
            6, six,
            7, seven,
            8, eight,
            9, nine
        );
    }

    private static Set<Character> findUniqueSignal(Set<Set<Character>> signals, int size) {
        return signals.stream()
            .filter(x -> x.size() == size)
            .findFirst()
            .orElseThrow();
    }

}
