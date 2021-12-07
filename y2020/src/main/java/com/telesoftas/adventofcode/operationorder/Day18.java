package com.telesoftas.adventofcode.operationorder;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day18 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day18.txt")) {
            log.info(
                "Answer: {}",
                toStringLines(requireNonNull(input))
                    .mapToLong(OperationOrder::evaluateExpression)
                    .sum()
            );
        }

        try (InputStream input = ClassLoader.getSystemResourceAsStream("day18.txt")) {
            log.info(
                "Answer: {}",
                toStringLines(requireNonNull(input))
                    .mapToLong(OperationOrder::evaluateExpressionWithAdditionPrecedence)
                    .sum()
            );
        }
    }


    public static Stream<String> toStringLines(InputStream batch) {
        return new Scanner(batch)
            .useDelimiter("\n")
            .tokens();
    }

}
