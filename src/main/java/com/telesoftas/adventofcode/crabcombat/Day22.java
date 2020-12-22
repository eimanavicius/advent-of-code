package com.telesoftas.adventofcode.crabcombat;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

@Log4j2
public class Day22 {

    public static void main(String[] args) throws IOException {
        try (InputStream input = ClassLoader.getSystemResourceAsStream("day22.txt")) {
            final CrabCombat combat = toCrabCombat(requireNonNull(input));

            log.info("Answer: {}", combat.winningPlayerScore());
        }
    }

    public static CrabCombat toCrabCombat(InputStream input) {
        final Scanner scanner = new Scanner(input);

        Player player1 = readPlayer(scanner);

        scanner.nextLine();
        scanner.nextLine();

        Player player2 = readPlayer(scanner);

        return new CrabCombat(player1, player2);
    }

    private static Player readPlayer(Scanner scanner) {
        scanner.nextLine();
        Player player = new Player();
        while (scanner.hasNextInt()) {
            player.addCardToBottom(scanner.nextInt());
        }
        return player;
    }
}
