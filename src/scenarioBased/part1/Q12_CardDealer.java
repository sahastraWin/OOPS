package scenarioBased.part1;/*
/*
 * Question 12 - Chapter 4: Structures
 * Model a playing card with a structure containing suit
 * (clubs, diamonds, hearts, spades) and face value (Ace–King).
 * Write a program that randomly deals a hand of five cards.
 */

import java.util.*;

public class Q12_CardDealer {
    static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
    static final String[] FACES = {"Ace", "2", "3", "4", "5", "6", "7",
                                    "8", "9", "10", "Jack", "Queen", "King"};

    static class Card {
        int suit, face;
        Card(int suit, int face) { this.suit = suit; this.face = face; }
        @Override public String toString() { return FACES[face] + " of " + SUITS[suit]; }
    }

    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();
        for (int s = 0; s < 4; s++)
            for (int f = 0; f < 13; f++)
                deck.add(new Card(s, f));

        Collections.shuffle(deck);
        System.out.println("Your 5-card hand:");
        for (int i = 0; i < 5; i++)
            System.out.println("  " + deck.get(i));
    }
}
