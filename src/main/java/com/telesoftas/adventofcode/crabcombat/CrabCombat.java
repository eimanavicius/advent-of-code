package com.telesoftas.adventofcode.crabcombat;

public record CrabCombat(Player player1, Player player2) {

    public long winningPlayerScore() {
        while (player1.hasCards() && player2.hasCards()) {
            final int p1 = player1.drawTopCard();
            final int p2 = player2.drawTopCard();
            if (p1 > p2) {
                player1.addCardToBottom(p1);
                player1.addCardToBottom(p2);
            } else {
                player2.addCardToBottom(p2);
                player2.addCardToBottom(p1);
            }
        }
        if (player1.hasCards()) {
            return player1.score();
        }
        return player2.score();
    }
}
