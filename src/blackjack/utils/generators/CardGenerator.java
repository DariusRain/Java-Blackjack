package blackjack.utils.generators;

public class CardGenerator extends Generator {

    /**
    * Generates a random card suit.
    * @return String -> "♡ or ♣ or ♠ or ♢"
    */
    public static String suit() { return generate("♡|♣|♠|♢"); }

    /**
     * Generates a random card suit.
     * @return String -> "2 through 9 or 10 or Ace or King or Queen or Jack"
     */
    public static String face() { return generate("[2-9]|10|Ace|King|Queen|Jack"); }

    /**
     * Generates a random card suit and face.
     * @see CardGenerator#suit()
     * @see CardGenerator#face()
     * @return String -> suit() + face()
     */
    public static String nextCard() {
        return suit() + face();
    }
}
