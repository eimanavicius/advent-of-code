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
            final List<Instruction> instructions = new BufferedReader(new InputStreamReader(requireNonNull(input)))
                .lines()
                .map(s -> s.split(" "))
                .map(parts -> new Instruction(parts[0], parseInt(parts[1])))
                .collect(toList());

            Set<Integer> prev = new HashSet<>();

            Integer sum;
            try {
                sum = runProgram(instructions, prev);
            } catch (LoopException e) {
                log.info("Part 1 answer: {}", e.getSum());

                sum = tryRemoveInfiniteLoop(instructions, prev);
            }

            log.info("Part 2 answer: {}", sum);
        }
    }

    private static Integer tryRemoveInfiniteLoop(List<Instruction> instructions, Set<Integer> previousPositions) {
        for (int position : previousPositions) {
            final Instruction instr = instructions.get(position);

            if ("acc".equals(instr.command())) {
                continue;
            }

            String newCmd = switch (instr.command()) {
                case "jmp" -> "nop";
                case "nop" -> "jmp";
                default -> throw new IllegalStateException("Unexpected value: " + instr.command());
            };

            final ArrayList<Instruction> update = new ArrayList<>(instructions);
            update.set(position, instr.withCommand(newCmd));

            try {
                return runProgram(update);
            } catch (LoopException loopException) {
                // try different
            }
        }
        return null;
    }

    private static int runProgram(List<Instruction> instructions) throws LoopException {
        return runProgram(instructions, new HashSet<>());
    }

    private static int runProgram(List<Instruction> instructions, Set<Integer> prev) throws LoopException {
        int sum = 0;
        int jump;
        for (int i = 0; i < instructions.size(); i += jump) {
            if (prev.contains(i)) {
                throw new LoopException(sum);
            }
            prev.add(i);
            jump = 1;
            final Instruction instr = instructions.get(i);
            if ("acc".equals(instr.command())) {
                sum += instr.value();
            } else if ("jmp".equals(instr.command())) {
                jump = instr.value();
            }
        }
        return sum;
    }
}
