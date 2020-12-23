package com.telesoftas.adventofcode.crabcups;

import com.telesoftas.adventofcode.crabcups.CrabCups.CupsCircle.Node;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

@Log4j2
public class CrabCups {

    private final CupsCircle cups;

    public CrabCups(List<Integer> cups) {
        this.cups = new CupsCircle(cups);
    }

    public static CrabCups ofMillion(List<Integer> cupsSeed) {
        final List<Integer> cups = new ArrayList<>(1_000_000);
        cups.addAll(cupsSeed);
        for (int i = cups.size() + 1; i <= 1_000_000; i++) {
            cups.add(i);
        }
        return new CrabCups(cups);
    }

    public void moveCupsTimes(int moves) {
        int max = cups.max();
        for (int move = 0; move < moves; move++) {
            log.trace("-- move {} --", move + 1);
            log.trace(this::cupsToString);

            Node current = cups.pop();
            List<Node> pickUp = List.of(cups.pop(), cups.pop(), cups.pop());

            log.trace(() -> "pick up: " + String.join(
                ", ",
                pickUp.stream().map(Node::card).map(String::valueOf).collect(joining(", "))
            ));

            int destination = current.card - 1;
            while (pickUp.contains(new Node(null, destination, null)) || destination < 1) {
                destination--;
                if (destination < 1) {
                    destination = max;
                }
            }

            log.trace("destination: {}", destination);

            cups.pushAll(cups.nodeOf(destination), pickUp);
            cups.push(current);

            log.trace("");
        }

        log.trace("-- final --");
        log.trace(this::cupsToString);
        log.trace("");
    }

    public String cupsAfter1AsString(int moves) {
        moveCupsTimes(moves);

        StringBuilder line = new StringBuilder();
        Node afterOne = cups.nodeOf(1);

        for (Node next = afterOne.next; next != null; next = next.next) {
            line.append(next.card);
        }
        for (Node next = cups.head; next != afterOne; next = next.next) {
            line.append(next.card);
        }
        return line.toString();
    }

    public long twoCupsAfterCupOneMultiplied(int moves) {
        moveCupsTimes(moves);

        Node afterOne = cups.nodeOf(1);
        long product = 1;
        for (int i = 0; i < 2; i++) {
            if (afterOne.next == null) {
                afterOne = cups.head;
            } else {
                afterOne = afterOne.next;
            }
            product *= afterOne.card;
        }
        return product;
    }

    private String cupsToString() {
        StringBuilder builder = new StringBuilder("cups: (");
        builder.append(cups.head.card);
        builder.append(")");
        for (Node node = cups.head.next; node != null; node = node.next) {
            builder.append(" ");
            builder.append(node.card);
        }
        return builder.toString();
    }

    static class CupsCircle {

        final Map<Integer, Node> index;
        Node head;
        Node tail;

        public CupsCircle(List<Integer> cups) {
            final int size = cups.size();
            index = new HashMap<>(size);
            Node prev = head = new Node(null, cups.get(0), null);
            index.put(head.card, head);
            for (int i = 1; i < size; i++) {
                tail = prev = prev.next = new Node(prev, cups.get(i), null);
                index.put(tail.card, tail);
            }
        }

        public int max() {
            return index.keySet().stream().max(Integer::compareTo).orElseThrow();
        }

        public Node pop() {
            final Node node = this.head;
            if (node.next != null) {
                node.next.prev = null;
            }
            head = node.next;
            return node;
        }

        public Node nodeOf(int card) {
            return index.get(card);
        }

        public void push(Node node) {
            node.prev = tail;
            tail = tail.next = node;
            node.next = null;
        }

        public void pushAll(Node start, List<Node> nodes) {
            if (start.next == null) {
                nodes.forEach(this::push);
                return;
            }
            Node next = start.next;
            Node prev = start;
            for (Node node : nodes) {
                node.prev = prev;
                prev = prev.next = node;
            }
            prev.next = next;
            next.prev = prev;
        }

        @EqualsAndHashCode(of = {"card"})
        static class Node {

            int card;
            Node next;
            Node prev;

            Node(Node prev, int card, Node next) {
                this.card = card;
                this.next = next;
                this.prev = prev;
            }

            int card() {
                return card;
            }
        }
    }
}
