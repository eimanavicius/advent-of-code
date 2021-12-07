package com.telesoftas.adventofcode.handyhaversacks;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(of = {"name"})
public class Bag {

    public static final Bag SHINY_GOLD = new Bag("shiny gold");

    String name;

    int amount;

    public Bag(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public Bag(String name) {
        this(name, 0);
    }
}
