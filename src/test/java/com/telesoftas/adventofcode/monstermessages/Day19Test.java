package com.telesoftas.adventofcode.monstermessages;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
