package com.telesoftas.adventofcode.jurassicjigsaw;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.Long.parseLong;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@Log4j2
public class Day20 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day20.txt")) {
            final JurassicJigsaw jigsaw = toJurassicJigsaw(requireNonNull(input));

            log.info("Answer: {}", jigsaw.multiplyCornerTileIds());
            log.info("Answer: {}", jigsaw.determineWaterRoughness());
        }
    }

    public static JurassicJigsaw toJurassicJigsaw(InputStream input) {
        return new Scanner(input)
            .useDelimiter("\n\n")
            .tokens()
            .map(tileString -> {
                final String[] split = tileString.split("\n");
                final long id = parseLong(split[0].substring("Tile ".length(), split[0].length() - 1));
                char[][] map = new char[split.length - 1][];
                for (int i = 1; i < split.length; i++) {
                    map[i - 1] = split[i].toCharArray();
                }
                return new Tile(id, map);
            })
            .collect(collectingAndThen(toList(), JurassicJigsaw::fromTilesList));
    }
}
