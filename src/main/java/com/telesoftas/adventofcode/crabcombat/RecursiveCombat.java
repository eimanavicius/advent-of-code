package com.telesoftas.adventofcode.crabcombat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public record RecursiveCombat(Player player1, Player player2) {

    public long winningPlayerScore() {
        return determineWinningPlayer().score();
    }

    private Player determineWinningPlayer() {
        Set<List<Integer>> previous = new HashSet<>();

        while (player1.hasCards() && player2.hasCards()) {
            if (!previous.add(player1.cards())) {
                return player1;
            }

            final int card1 = player1.drawTopCard();
            final int card2 = player2.drawTopCard();

            if (roundWinner(card1, card2).equals(player1)) {
                player1.winRound(card1, card2);
            } else {
                player2.winRound(card2, card1);
            }
        }

        if (player1.hasCards()) {
            return player1;
        }

        return player2;
    }

    private Player roundWinner(int card1, int card2) {
        if (player1.hasAtLeast(card1) && player2.hasAtLeast(card2)) {
            return new RecursiveCombat(
                player1.subPlayer(card1),
                player2.subPlayer(card2)
            )
                .determineWinningPlayer();
        }

        return (card1 > card2) ? player1 : player2;
    }
}
