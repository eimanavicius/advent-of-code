package com.telesoftas.adventofcode.handyhaversacks;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Set;

@Value
@EqualsAndHashCode(of = {"bagColor"})
public class BagRule {

    String bagColor;
    Set<String> innerBagsColors;

    public BagRule(String bagColor, Set<String> innerBagsColors) {
        this.bagColor = bagColor;
        this.innerBagsColors = innerBagsColors;
    }

    public boolean canContain(String bagColor) {
       return innerBagsColors.contains(bagColor);
    }
}
