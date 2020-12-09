package com.telesoftas.adventofcode.encodingerror;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.telesoftas.adventofcode.encodingerror.EncodingError.findEncryptionError;
import static com.telesoftas.adventofcode.encodingerror.EncodingError.findEncryptionWeakness;
import static com.telesoftas.adventofcode.encodingerror.EncodingError.findEncryptionWeaknessRange;
import static com.telesoftas.adventofcode.encodingerror.EncodingError.toNumbersList;
import static java.util.Objects.requireNonNull;

@Log4j2
public class Day9 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day9.txt")) {
            List<Long> numbers = toNumbersList(requireNonNull(input));

            // What is the first number that does not have this property?
            Long erroneous = findEncryptionError(numbers, 25);
            log.info("Answer: {}", erroneous);

            // What is the encryption weakness in your XMAS-encrypted list of numbers?
            Long weakness = findEncryptionWeakness(findEncryptionWeaknessRange(numbers, erroneous));
            log.info("Answer: {}", weakness);
        } catch (EncryptionErrorNotFoundException e) {
            log.error(e);
        }
    }
}
