package com.telesoftas.adventofcode.monstermessages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MonsterMessagesTest {

    private RuleSet rules;

    @BeforeEach
    void setUp() {
        rules = new RuleSet();
        rules.putSequenceMatch(0, Set.of(new int[]{4, 1, 5}));
        rules.putSequenceMatch(1, Set.of(new int[]{2, 3}, new int[]{3, 2}));
        rules.putSequenceMatch(2, Set.of(new int[]{4, 4}, new int[]{5, 5}));
        rules.putSequenceMatch(3, Set.of(new int[]{4, 5}, new int[]{5, 4}));
        rules.putSymbolMatch(4, 'a');
        rules.putSymbolMatch(5, 'b');
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "aaaabb",
        "aaabab",
        "abbabb",
        "abbbab",
        "aabaab",
        "aabbbb",
        "abaaab",
        "ababbb"
    })
    void valid_samples(String message) {
        assertTrue(rules.isMessageValid(message));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "a",       // too short
        "aaababa", // too long
        "aaabbb",  // do not match
    })
    void invalid_sample(String message) {
        assertFalse(rules.isMessageValid(message));
    }
}
