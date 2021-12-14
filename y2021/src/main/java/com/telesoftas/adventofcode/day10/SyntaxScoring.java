package com.telesoftas.adventofcode.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SyntaxScoring {

    public static final Set<Character> OPEN_CHARS = Set.of('(', '{', '[', '<');
    public static final Map<Character, Character> CLOSE_TO_OPEN = Map.of(
        ')', '(',
        '}', '{',
        ']', '[',
        '>', '<'
    );
    private static final Map<Character, Integer> CLOSE_TO_SCORE = Map.of(
        ')', 3,
        ']', 57,
        '}', 1197,
        '>', 25137
    );

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("day10.txt").getPath()));

        int score = findSyntaxErrorScore(lines);

        System.out.println("What is the total syntax error score for those errors?");
        System.out.println(score);
    }

    public static int findSyntaxErrorScore(List<String> lines) {
        return lines.stream()
            .mapToInt(line -> {
                Deque<Character> open = new ArrayDeque<>();

                for (Character c : line.toCharArray()) {
                    if (OPEN_CHARS.contains(c)) {
                        open.push(c);
                    } else if (CLOSE_TO_OPEN.get(c).equals(open.peek())) {
                        open.pop();
                    } else {
                        return CLOSE_TO_SCORE.get(c);
                    }
                }

                return 0;
            })
            .sum();
    }
}
