package com.telesoftas.adventofcode.passwordphilosophy;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static java.util.Optional.empty;
import static java.util.Optional.of;

@UtilityClass
public class PasswordPhilosophy {

    private static final Pattern PATTERN = Pattern.compile("(?<min>\\d+)-(?<max>\\d+) (?<char>\\w): (?<pswd>.*)");

    public static Optional<SelfValidatingPassword> stringToSelfValidatingPassword(String line) {
        return stringToSelfValidatingPassword(line, CharCountPredicate::new);
    }

    public static Optional<SelfValidatingPassword> stringToSelfValidatingPassword(
        String line,
        PredicateFactory factory
    ) {
        Matcher matcher = PATTERN.matcher(line);

        if (!matcher.matches()) {
            return empty();
        }

        return of(new SelfValidatingPassword(
            new Password(matcher.group("pswd").getBytes()),
            factory.create(
                matcher.group("char").charAt(0),
                parseInt(matcher.group("min")),
                parseInt(matcher.group("max"))
            )
        ));
    }

    public static Stream<String> toPasswordWithPolicyAsStringStream(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter("\n")
            .tokens();
    }

    public interface PredicateFactory {

        Predicate<byte[]> create(char letter, int one, int two);
    }
}
