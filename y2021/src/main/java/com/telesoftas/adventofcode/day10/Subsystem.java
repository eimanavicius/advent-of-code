package com.telesoftas.adventofcode.day10;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Subsystem {

    public static final Subsystem VALID = new Subsystem(new ArrayDeque<>(), (char) 0);
    private static final Map<Character, Integer> CLOSE_TO_SCORE = Map.of(
        ')', 3,
        ']', 57,
        '}', 1197,
        '>', 25137
    );
    private final Deque<Character> open;
    private final Character current;

    public Subsystem(Deque<Character> open, Character current) {
        this.open = open;
        this.current = current;
    }

    public int getSyntaxErrorScore() {
        return CLOSE_TO_SCORE.get(current);
    }

    public boolean isCorrupted() {
        return !open.isEmpty() && current != 0;
    }
}
