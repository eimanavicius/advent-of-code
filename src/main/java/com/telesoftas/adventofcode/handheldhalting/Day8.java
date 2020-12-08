package com.telesoftas.adventofcode.handheldhalting;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day8 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day8.txt")) {
            final List<Intructution> instructions = new BufferedReader(new InputStreamReader(requireNonNull(input)))
                .lines()
                .map(s -> {
                    final String[] parts = s.split(" ");
                    return new Intructution(parts[0], Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toList());

            Set<Integer> prev = new HashSet<>();

            int sum = 0;
            int jump;
            for (int i = 0; i < instructions.size(); i += jump) {
                if (prev.contains(i)) {
                    break;
                }
                prev.add(i);
                jump = 1;
                final Intructution instr = instructions.get(i);
                if ("acc".equals(instr.getCommand())) {
                    sum += instr.getValue();
                } else if ("jmp".equals(instr.getCommand())) {
                    jump = instr.getValue();
                }
            }

            log.info("Answer: {}", sum);
        }
    }
}
