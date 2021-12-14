package com.telesoftas.adventofcode.day08;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SevenSegmentSearchTest {

    @Test
    void simple_sample_part_1() {
        int count = SevenSegmentSearch.count1478(List.of(
            new Line(
                List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split("\s")),
                List.of("fdgacbe cefdb cefbgd gcbe".split("\s"))
            )
        ));

        assertEquals(2, count);
    }

    @Test
    void full_sample_part_1() {
        List<Line> input = SevenSegmentSearch.toLines(ClassLoader.getSystemResourceAsStream("day08_sample.txt"));

        int count = SevenSegmentSearch.count1478(input);

        assertEquals(26, count);
    }

    @Test
    void simple_sample_part_2() {
        int sum = SevenSegmentSearch.sumOutputValues(List.of(
            new Line(
                List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split("\s")),
                List.of("cdfeb fcadb cdfeb cdbaf".split("\s"))
            )
        ));

        assertEquals(5353, sum);
    }

    @Test
    void sample_part_2() {
        List<Line> input = SevenSegmentSearch.toLines(ClassLoader.getSystemResourceAsStream("day08_sample.txt"));

        int sum = SevenSegmentSearch.sumOutputValues(input);

        assertEquals(61229, sum);
    }

    private Set<Set<Character>> getSignalSets() {
        return Stream.of("cagedb", "ab", "gcdfa", "fbcad", "eafb", "cdfbe", "cdfgeb", "dab", "acedgfb", "cefabd")
            .map(Line::stringToSetOfChars)
            .collect(toSet());
    }

    @Test
    void detect_one_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Set.of('a', 'b'), numbers.get(1));
    }

    @Test
    void detect_four_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Set.of('e', 'a', 'f', 'b'), numbers.get(4));
    }

    @Test
    void detect_tree_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Set.of('f', 'b', 'c', 'a', 'd'), numbers.get(3));
    }

    @Test
    void detect_seven_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Set.of('d', 'a', 'b'), numbers.get(7));
    }

    @Test
    void detect_eight_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Set.of('a', 'c', 'e', 'd', 'g', 'f', 'b'), numbers.get(8));
    }

    @Test
    void detect_two_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Line.stringToSetOfChars("gcdfa"), numbers.get(2));
    }

    @Test
    void detect_five_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Line.stringToSetOfChars("cdfbe"), numbers.get(5));
    }

    @Test
    void detect_zero_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Line.stringToSetOfChars("cagedb"), numbers.get(0));
    }

    @Test
    void detect_six_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Line.stringToSetOfChars("cefabd"), numbers.get(9));
    }

    @Test
    void detect_nine_from_signals_set() {
        var numbers = SevenSegmentSearch.numberToSignalMap(getSignalSets());

        assertEquals(Line.stringToSetOfChars("cdfgeb"), numbers.get(6));
    }
}
