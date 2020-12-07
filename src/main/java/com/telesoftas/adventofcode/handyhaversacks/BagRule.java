package com.telesoftas.adventofcode.handyhaversacks;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Set;

@Value
@EqualsAndHashCode(of = {"bag"})
public class BagRule {

    Bag bag;
    Set<Bag> innerBags;

    public BagRule(Bag bag, Set<Bag> innerBags) {
        this.bag = bag;
        this.innerBags = innerBags;
    }

    public boolean canContain(Bag bagColor) {
       return innerBags.contains(bagColor);
    }
}
