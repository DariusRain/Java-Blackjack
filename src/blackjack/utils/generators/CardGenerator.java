package blackjack.utils.generators;

public class CardGenerator extends Generator {

    /**
          * Generates a random card suit.
          * @return String ->
     */
    public static String suit() { return generate("♡|♣|♠|♢"); }

    public static String face() { return generate("[2-9]|10|Ace|King|Queen|Jack"); }

    public static String nextCard() {
        return suit() + face();
    }
}
