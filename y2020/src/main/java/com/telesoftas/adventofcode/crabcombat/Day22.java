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
            final Player[] players = readPlayers(requireNonNull(input));

            final CrabCombat crab = new CrabCombat(players[0].clone(), players[1].clone());
            log.info("Answer: {}", crab.winningPlayerScore());

            final RecursiveCombat recursive = new RecursiveCombat(players[0].clone(), players[1].clone());
            log.info("Answer: {}", recursive.winningPlayerScore());
        }
    }

    private static Player[] readPlayers(InputStream input) {
        final Scanner scanner = new Scanner(input);

        Player player1 = readPlayer(scanner);

        scanner.nextLine();
        scanner.nextLine();

        Player player2 = readPlayer(scanner);

        return new Player[]{player1, player2};
    }

    private static Player readPlayer(Scanner scanner) {
        final String name = scanner.findInLine("\\d+");
        scanner.nextLine();
        Player player = new Player(name);
        while (scanner.hasNextInt()) {
            player.addCardToBottom(scanner.nextInt());
        }
        return player;
    }
}
