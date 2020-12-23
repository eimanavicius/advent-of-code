package com.telesoftas.adventofcode.crabcups;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Day23 {

    public static void main(String[] args) {
        final CrabCups crabCups = new CrabCups(List.of(4, 1, 8, 9, 7, 6, 2, 3, 5));

        log.info("Answer: {}", crabCups.cupsAfter1AsString(100));
    }
}
