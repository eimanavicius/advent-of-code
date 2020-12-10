package com.telesoftas.adventofcode.passwordphilosophy;

import lombok.Value;

import java.util.function.Predicate;

@Value
public class ExactlyOnePositionPredicate implements Predicate<byte[]> {

    char letter;

    int first;

    int second;

    @Override
    public boolean test(byte[] value) {
        byte one = value[first - 1];
        byte two = value[second - 1];
        return one == letter ^ two == letter;
    }
}
