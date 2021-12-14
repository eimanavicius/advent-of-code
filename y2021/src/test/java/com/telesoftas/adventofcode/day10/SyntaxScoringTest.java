package com.telesoftas.adventofcode.day10;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SyntaxScoringTest {

    @Test
    void corrupted_line_1() {
        int score = SyntaxScoring.findSyntaxErrorScore(List.of("(]"));

        assertEquals(57, score);
    }

    @Test
    void corrupted_line_2() {
        int score = SyntaxScoring.findSyntaxErrorScore(List.of("[)"));

        assertEquals(3, score);
    }

    @Test
    void sample_part_1() {
        int score = SyntaxScoring.findSyntaxErrorScore(List.of(
            "[({(<(())[]>[[{[]{<()<>>",
            "[(()[<>])]({[<{<<[]>>(",
            "{([(<{}[<>[]}>{[]{[(<()>",
            "(((({<>}<{<{<>}{[]{[]{}",
            "[[<[([]))<([[{}[[()]]]",
            "[{[{({}]{}}([{[{{{}}([]",
            "{<[[]]>}<{[{[{[]{()[[[]",
            "[<(<(<(<{}))><([]([]()",
            "<{([([[(<>()){}]>(<<{{",
            "<{([{{}}[<[[[<>{}]]]>[]]"
        ));

        assertEquals(26397, score);
    }

    @Test
    void how_deque_works() {
        Deque<Character> open = new ArrayDeque<>();

        open.push('a');
        open.push('b');

        assertArrayEquals(new Character[]{'b', 'a'}, open.toArray());

        assertEquals('b', open.peek());

        open.pop();

        assertArrayEquals(new Character[]{'a'}, open.toArray());
    }
}
