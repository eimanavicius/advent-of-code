package com.telesoftas.adventofcode.handheldhalting;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day8 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day8.txt")) {
            final List<Intructution> instructions = new BufferedReader(new InputStreamReader(requireNonNull(input)))
                .lines()
                .map(s -> s.split(" "))
                .map(parts -> new Intructution(parts[0], parseInt(parts[1])))
                .collect(toList());

            Set<Integer> prev = new HashSet<>();

            Integer sum;
            try {
                sum = runProgram(instructions, prev);
            } catch (LoopException e) {
                sum = tryRemoveInfiniteLoop(instructions, prev);
            }

            log.info("Answer: {}", sum);
        }
    }

    private static Integer tryRemoveInfiniteLoop(List<Intructution> instructions, Set<Integer> previousPositions) {
        for (int position : previousPositions) {
            final Intructution instr = instructions.get(position);
            if ("jmp".equals(instr.getCommand())) {
                final ArrayList<Intructution> update = new ArrayList<>(instructions);
                update.set(position, instr.withCommand("nop"));
                try {
                    return runProgram(update);
                } catch (LoopException loopException) {
                    // try different
                }
            } else if ("nop".equals(instr.getCommand())) {
                final ArrayList<Intructution> update = new ArrayList<>(instructions);
                update.set(position, instr.withCommand("jmp"));
                try {
                    return runProgram(update);
                } catch (LoopException loopException) {
                    // try different
                }
            }
        }
        return null;
    }

    private static int runProgram(List<Intructution> instructions) throws LoopException {
        return runProgram(instructions, new HashSet<>());
    }

    private static int runProgram(List<Intructution> instructions, Set<Integer> prev) throws LoopException {
        int sum = 0;
        int jump;
        for (int i = 0; i < instructions.size(); i += jump) {
            if (prev.contains(i)) {
                throw new LoopException(sum);
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
        return sum;
    }
}
