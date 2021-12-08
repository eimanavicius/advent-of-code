package com.telesoftas.adventofcode.day08;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SevenSegmentSearchTest {

    @Test
    void simple_sample_part_1() {
        List<Line> input = List.of(
            new Line(
                List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab".split("\s")),
                List.of("fdgacbe cefdb cefbgd gcbe".split("\s"))
            )
        );

        int count = SevenSegmentSearch.count1478(input);

        assertEquals(2, count);
    }

    @Test
    void full_sample_part_1() {
        List<Line> input = SevenSegmentSearch.toLines(ClassLoader.getSystemResourceAsStream("day08_sample.txt"));

        int count = SevenSegmentSearch.count1478(input);

        assertEquals(26, count);
    }
}
