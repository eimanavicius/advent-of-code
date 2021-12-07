package com.telesoftas.adventofcode.passwordphilosophy;

import lombok.Value;

import java.util.function.Predicate;

@Value
public class CharCountPredicate implements Predicate<byte[]> {

    char letter;

    int min;

    int max;

    @Override
    public boolean test(byte[] value) {
        return withinRange(value);
    }

    public boolean withinRange(byte[] value) {
        int count = countLetters(value);

        return min <= count && count <= max;
    }

    private int countLetters(byte[] value) {
        var count = 0;
        for (byte symbol : value) {
            if (symbol == letter) {
                count++;
            }
        }

        return count;
    }
}
