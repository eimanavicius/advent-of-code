package com.telesoftas.adventofcode.crabcombat;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = {"name"})
public class Player implements Cloneable {

    private final List<Integer> cards;
    private final String name;

    public Player(String name, List<Integer> cards) {
        this.name = name;
        this.cards = new ArrayList<>(cards);
    }

    public Player(String name) {
        this(name, new ArrayList<>());
    }

    public Player subPlayer(int amount) {
        return new Player(name, cards.subList(0, amount));
    }

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

    public boolean hasAtLeast(int amount) {
        return cards.size() >= amount;
    }

    public List<Integer> cards() {
        return new ArrayList<>(cards);
    }

    public void winRound(int myCard, int opponentCard) {
        addCardToBottom(myCard);
        addCardToBottom(opponentCard);
    }

    @Override
    protected Player clone() {
        return new Player(name, cards);
    }
}
