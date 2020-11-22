package blackjack.utils.generators;

import blackjack.utils.UserInteractions.Console;

public class CardGenerator extends Generator {
    private static final String[] SUITS = {"♢", "♠", "♡", "♣"};
    private static final String[] FACES = {"Jack", "Queen", "King", "Ace"};
    private static final int FACE_VALUES = 13;
    public static String suit() {
        Console.logf("Suit: ");
        return SUITS[generate(SUITS.length - 1)]; }
    public static String face() {
        Console.logf("Face: ");
        int faceValue = generate(FACE_VALUES);
        if (faceValue <= 10 && faceValue > 1) {
            return faceValue + "";
        } else {
            return FACES[generate(FACES.length - 1)];
        }
    }
    public static String nextCard() {
        return suit() + face();
    }

}
