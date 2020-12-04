package com.telesoftas.adventofcode.passwordphilosophy;

import lombok.Value;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

@Value
public class SelfValidatingPassword {

    Password password;

    Predicate<byte[]> predicate;

    public boolean isValid() {
        return password.apply(predicate::test);
    }

    public boolean isValidThrow() {
        try {
            password.consume((byte[] value) -> {
                if (!predicate.test(value)) {
                    throw new RuntimeException();
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isValidAtomic() {
        AtomicBoolean result = new AtomicBoolean(false);

        password.consume((byte[] value) -> {
            result.set(predicate.test(value));
        });

        return result.get();
    }
}
