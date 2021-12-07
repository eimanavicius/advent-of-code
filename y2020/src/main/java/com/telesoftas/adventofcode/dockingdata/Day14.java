package com.telesoftas.adventofcode.dockingdata;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.regex.Pattern.compile;

@Log4j2
public class Day14 {

    public static final Pattern PATTERN = compile("(mask = (?<mask>[01X]+))|(mem\\[(?<index>\\d+)] = (?<value>\\d+))");

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day14.txt")) {
            Map<String, Integer> mem = new HashMap<>();
            toLinesStream(requireNonNull(input))
                .map(PATTERN::matcher)
                .filter(Matcher::matches)
                .forEach(new Consumer<>() {

                    private byte[] mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX".getBytes();

                    @Override
                    public void accept(Matcher matcher) {
                        final String maskAsString = matcher.group("mask");
                        if (maskAsString != null) {
                            mask = maskAsString.getBytes();
                            return;
                        }

                        final Integer index = Integer.valueOf(matcher.group("index"));
                        final Integer value = Integer.valueOf(matcher.group("value"));
                        final byte[] binaryIndex = toBinaryBytes36(index);

                        for (int i = mask.length - 1; i >= 0; i--) {
                            if ('0' != mask[i]) {
                                binaryIndex[i] = mask[i];
                            }
                        }

                        List<byte[]> indexes = resolveFloatingIndexes(binaryIndex);

                        String binaryString;
                        for (byte[] newIndex : indexes) {
                            binaryString = new String(newIndex);
                            mem.put(binaryString, value);
                        }
                    }
                });

            log.info("Answer: {}", mem.values().stream().mapToLong(value -> value).sum());
        }
    }

    public static List<byte[]> resolveFloatingIndexes(byte[] source) {
        final ArrayList<byte[]> indexes = new ArrayList<>();
        indexes.add(source);

        final int length = source.length;
        for (int i = 0; i < length; i++) {
            if (source[i] == 'X') {
                for (int j = 0, indexesSize = indexes.size(); j < indexesSize; j++) {
                    byte[] first = indexes.get(j);
                    final byte[] second = Arrays.copyOf(first, length);
                    first[i] = '0';
                    second[i] = '1';
                    indexes.add(second);
                }
            }
        }

        return indexes;
    }

    private static byte[] toBinaryBytes36(Integer value) {
        final byte[] binaryValue = Integer.toBinaryString(value).getBytes();
        byte[] all = new byte[36];
        Arrays.fill(all, (byte) '0');
        for (int i = all.length - 1, j = binaryValue.length - 1; i >= 0 && j >= 0; i--, j--) {
            all[i] = binaryValue[j];
        }
        return all;
    }

    public static Stream<String> toLinesStream(InputStream input) {
        return new Scanner(input)
            .useDelimiter("\n")
            .tokens();
    }
}
