package com.telesoftas.adventofcode.day10;

import java.util.Deque;
import java.util.List;
import java.util.Map;

public class Subsystem {

    public static final Map<Character, Character> OPEN_TO_CLOSE = Map.of(
        '(', ')',
        '{', '}',
        '[', ']',
        '<', '>'
    );
    private static final Map<Character, Integer> CLOSE_TO_SCORE = Map.of(
        ')', 3,
        ']', 57,
        '}', 1197,
        '>', 25137
    );
    private final Deque<Character> open;
    private final Character current;

    public Subsystem(Deque<Character> open) {
        this(open, (char) 0);
    }

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

    public boolean isIncomplete() {
        return !open.isEmpty() && current == 0;
    }

    public List<Character> autocomplete() {
        return open.stream().map(OPEN_TO_CLOSE::get).toList();
    }
}
