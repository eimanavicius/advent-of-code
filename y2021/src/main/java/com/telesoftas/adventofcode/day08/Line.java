package com.telesoftas.adventofcode.day08;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public record Line(List<String> input, List<String> output) {

    public Set<Set<Character>> toUniqueSignals() {
        return Stream.of(output, input)
            .flatMap(strings -> strings.stream().map(Line::stringToSetOfChars))
            .collect(toSet());
    }

    public static Set<Character> stringToSetOfChars(String signal) {
        return signal.chars().mapToObj(segment -> (char) segment).collect(toSet());
    }

    public Stream<Set<Character>> streamOutputCharsets() {
        return output.stream().map(Line::stringToSetOfChars);
    }
}
