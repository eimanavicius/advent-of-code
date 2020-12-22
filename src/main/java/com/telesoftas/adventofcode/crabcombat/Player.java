package com.telesoftas.adventofcode.crabcombat;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final List<Integer> cards = new ArrayList<>();

    public void addCardToBottom(int card) {
        cards.add(card);
    }

    public boolean hasCards() {
        return !cards.isEmpty();
    }

    public int drawTopCard() {
        return cards.remove(0);
    }

    public long score() {
        final int size = cards.size();
        int score = 0;
        for (int i = 0; i < size; i++) {
            score += (size - i) * cards.get(i);
        }
        return score;
    }
}
