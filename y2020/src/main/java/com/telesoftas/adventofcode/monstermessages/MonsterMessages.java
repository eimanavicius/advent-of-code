package com.telesoftas.adventofcode.monstermessages;

import java.util.List;

public record MonsterMessages(RuleSet rules, List<String> messages) {

    public long countValidMessages() {
        return messages.stream().filter(rules::isMessageValid).count();
    }

    public long countLoopValidMessages() {
        return messages.stream().filter(rules::isMessageValidSpecialized0Loops).count();
    }
}
