package com.telesoftas.adventofcode.passwordphilosophy;

import lombok.extern.log4j.Log4j2;

import java.io.InputStream;
import java.util.Optional;

@Log4j2
public class Day2 {

    public static void main(String[] args) {
        InputStream input = ClassLoader.getSystemResourceAsStream("day2.txt");

        long count = PasswordPhilosophy.toPasswordWithPolicyAsStringStream(input)
            .map(PasswordPhilosophy::stringToSelfValidatingPassword)
            .flatMap(Optional::stream)
            .filter(SelfValidatingPassword::isValid)
            .count();

        log.info("Answer: {}", count);
    }
}
