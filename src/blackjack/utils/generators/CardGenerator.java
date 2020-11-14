package blackjack.utils.generators;

import static javax.swing.text.html.HTML.Tag.U;

public class CardGenerator extends Generator {

    /**
          * Generates a random card suit.
          * @return String ->
     */
    public static String suit() {

        String regex = "♡|♣|♠|♢";
        String suit = createGenerator(regex).generate();
        //  https://www.geeksforgeeks.org/assertions-in-java/
        assert suit.matches(regex);
        return suit;
    }

    public static String face() {
        String regex = "[2-9]|10|Ace|King|Queen|Jack";
        String face = createGenerator(regex).generate();
        assert face.matches(regex);
        return face;
    }

    public static String nextCard() {
        return suit() + face();
    }
}
