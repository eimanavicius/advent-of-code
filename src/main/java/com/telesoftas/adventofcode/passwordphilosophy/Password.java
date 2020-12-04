package com.telesoftas.adventofcode.passwordphilosophy;

import lombok.Value;

import java.util.function.Consumer;
import java.util.function.Function;

@Value
public class Password {

    byte[] value;

    public <T> T apply(Function<byte[], T> function) {
        return function.apply(value);
    }

    public void consume(Consumer<byte[]> consumer) {
        consumer.accept(value);
    }
}
