package com.telesoftas.adventofcode.passportprocessing;

import lombok.experimental.UtilityClass;

import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

@UtilityClass
public class PassportsBatch {

    public static Stream<String> toPassportAsStringStream(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter("\n\n")
            .tokens();
    }

    public static Stream<Passport> toPassportStream(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter("\n\n")
            .tokens()
            .map(Passport::fromString);
    }
}
