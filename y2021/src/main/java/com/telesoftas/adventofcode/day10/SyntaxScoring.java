package com.telesoftas.adventofcode.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

public class SyntaxScoring {

    public static final Set<Character> OPEN_CHARS = Set.of('(', '{', '[', '<');
    public static final Map<Character, Character> CLOSE_TO_OPEN = Map.of(
        ')', '(',
        '}', '{',
        ']', '[',
        '>', '<'
    );
    public static final Map<Character, Integer> AUTO_SCORE = Map.of(
        ')', 1,
        ']', 2,
        '}', 3,
        '>', 4
    );

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("day10.txt").getPath()));

        int score = findSyntaxErrorScore(lines);

        System.out.println("What is the total syntax error score for those errors?");
        System.out.println(score);

        long autocompleteScore = findMiddleAutocompleteScore(lines);

        System.out.println("What is the middle autocomplete score?");
        System.out.println(autocompleteScore);
    }

    public static long findMiddleAutocompleteScore(List<String> lines) {
        List<Long> scores = streamSubsystems(lines)
            .filter(Subsystem::isIncomplete)
            .map(subsystem -> {
                long sum = 0;
                List<Character> autocomplete = subsystem.autocomplete();
                for (Character character : autocomplete) {
                    long score = AUTO_SCORE.get(character);
                    sum = sum * 5 + score;
                }
                return sum;
            })
            .sorted()
            .toList();
        return scores.get(scores.size() / 2);
    }

    public static int findSyntaxErrorScore(List<String> lines) {
        return streamSubsystems(lines)
            .mapToInt(subsystem -> {
                if (subsystem.isCorrupted()) {
                    return subsystem.getSyntaxErrorScore();
                }
                return 0;
            })
            .sum();
    }

    private static Stream<Subsystem> streamSubsystems(List<String> lines) {
        return lines.stream()
            .map(line -> {
                Deque<Character> open = new ArrayDeque<>();

                for (Character c : line.toCharArray()) {
                    if (OPEN_CHARS.contains(c)) {
                        open.push(c);
                    } else if (CLOSE_TO_OPEN.get(c).equals(open.peek())) {
                        open.pop();
                    } else {
                        return new Subsystem(open, c);
                    }
                }

                return new Subsystem(open);
            });
    }
}
