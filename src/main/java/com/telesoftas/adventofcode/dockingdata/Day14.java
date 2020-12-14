package com.telesoftas.adventofcode.dockingdata;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day14 {

    private static byte[] mask = new byte[]{};

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day14.txt")) {
            Map<Integer, Integer> mem = new HashMap<>();

            toActionStream(requireNonNull(input))
                .forEach(line -> {
                    if (line.startsWith("mask")) {
                        mask = line.substring("mask = ".length()).getBytes();
                        return;
                    }
                    final String[] parts = line.split("] = ");
                    final Integer index = Integer.valueOf(parts[0].substring("mem[".length()));
                    final Integer value = Integer.valueOf(parts[1]);
                    final byte[] binaryValue = toBytes(value);

                    int vi = binaryValue.length - 1;
                    for (int i = mask.length - 1; i >= 0 && vi >= 0; i--) {
                        if ('1' == mask[i]) {
                            binaryValue[vi] = '1';
                        } else if ('0' == mask[i]) {
                            binaryValue[vi] = '0';
                        }
                        vi--;
                    }
                    final String valueInBits = new String(binaryValue);
                    final Integer finalValue = Integer.valueOf(valueInBits, 2);
                    mem.put(index, finalValue);
                });

            log.info("Answer: {}", mem.values().stream().mapToInt(value -> value).sum());
        }
    }

    private static byte[] toBytes(Integer value) {
        final byte[] binaryValue = Integer.toBinaryString(value).getBytes();
        byte[] all = new byte[36];
        Arrays.fill(all, (byte) '0');
        for (int i = all.length - 1, j = binaryValue.length - 1; i >= 0 && j >= 0; i--, j--) {
            all[i] = binaryValue[j];
        }
        return all;
    }

    public static Stream<String> toActionStream(InputStream input) {
        return new Scanner(input)
            .useDelimiter("\n")
            .tokens();
    }
}
