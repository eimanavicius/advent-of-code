package com.telesoftas.adventofcode.reportrepair;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day1 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day1.txt")) {
            long count = ExpenseReport.toIntegersStream(requireNonNull(input))
                .collect(collectingAndThen(toList(), ExpenseReport::find3));

            log.info("Answer: {}", count);
        } catch (NothingSumsTo2020Exception cause) {
            log.error(cause);
        }
    }
}
