package com.telesoftas.adventofcode.monstermessages;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class Day19Test {

    @Test
    void sample1() {
        final MonsterMessages monsterMessages = Day19.toMonsterMessages(new ByteArrayInputStream("""
            0: 1 2
            1: "a"
            2: 1 3 | 3 1
            3: "b"
                        
            aab
            aba
            """.getBytes()));

        assertEquals(2, monsterMessages.countValidMessages());
    }

    @Test
    void sample2() {
        final MonsterMessages monsterMessages = Day19.toMonsterMessages(new ByteArrayInputStream("""
            0: 4 1 5
            1: 2 3 | 3 2
            2: 4 4 | 5 5
            3: 4 5 | 5 4
            4: "a"
            5: "b"
                        
            ababbb
            bababa
            abbbab
            aaabbb
            aaaabbb
            """.getBytes()));

        assertEquals(2, monsterMessages.countValidMessages());
    }

    @Test
    void sample3() {
        final MonsterMessages monsterMessages = new MonsterMessages(sample3RuleSet(), sample3MessageList());

        assertEquals(3, monsterMessages.countValidMessages());
    }

    @Test
    void sample3_with_loops() {
        final MonsterMessages monsterMessages = new MonsterMessages(sample3RuleSet(), sample3MessageList());

        assertEquals(12, monsterMessages.countLoopValidMessages());
    }

    @Test
    void sample3_with_loops_both_parts_must_match() {
        final RuleSet ruleSet = sample3RuleSet();

        assertFalse(ruleSet.isMessageValidSpecialized0Loops("aaaabbaaaabbaaa"));
    }

    private RuleSet sample3RuleSet() {
        return Day19.readRuleSet(new Scanner(new ByteArrayInputStream("""
            42: 9 14 | 10 1
            9: 14 27 | 1 26
            10: 23 14 | 28 1
            1: "a"
            11: 42 31
            5: 1 14 | 15 1
            19: 14 1 | 14 14
            12: 24 14 | 19 1
            16: 15 1 | 14 14
            31: 14 17 | 1 13
            6: 14 14 | 1 14
            2: 1 24 | 14 4
            0: 8 11
            13: 14 3 | 1 12
            15: 1 | 14
            17: 14 2 | 1 7
            23: 25 1 | 22 14
            28: 16 1
            4: 1 1
            20: 14 14 | 1 15
            3: 5 14 | 16 1
            27: 1 6 | 14 18
            14: "b"
            21: 14 1 | 1 14
            25: 1 1 | 1 14
            22: 14 14
            8: 42
            26: 14 22 | 1 20
            18: 15 15
            7: 14 5 | 1 21
            24: 14 1
            """.getBytes())));
    }

    private List<String> sample3MessageList() {
        return List.of(
            "abbbbbabbbaaaababbaabbbbabababbbabbbbbbabaaaa",
            "bbabbbbaabaabba",
            "babbbbaabbbbbabbbbbbaabaaabaaa",
            "aaabbbbbbaaaabaababaabababbabaaabbababababaaa",
            "bbbbbbbaaaabbbbaaabbabaaa",
            "bbbababbbbaaaaaaaabbababaaababaabab",
            "ababaaaaaabaaab",
            "ababaaaaabbbaba",
            "baabbaaaabbaaaababbaababb",
            "abbbbabbbbaaaababbbbbbaaaababb",
            "aaaaabbaabaaaaababaa",
            "aaaabbaaaabbaaa",
            "aaaabbaabbaaaaaaabbbabbbaaabbaabaaa",
            "babaaabbbaaabaababbaabababaaab",
            "aabbbbbaabbbaaaaaabbbbbababaaaaabbaaabba"
        );
    }
}
