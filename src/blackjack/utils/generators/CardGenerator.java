package blackjack.utils.generators;

import blackjack.materials.Deck;

/**
* <h1>CardGenerator</h1>
* <p>
*   This class generates any player card based on random integer values that could be used to for the face
 *   value or an index in a array of strings for special non number cards.  Could do everything with numbers but wanted
 *   to give the user the comfort of readability. Cards will be parsed as values using the Parser class and the methods
 *   it contains.
* </p>
 * @see Deck#draw()
 * @see blackjack.utils.UserInteractions.Parser
 * @see blackjack.utils.UserInteractions.Parser#cardValue(String, int)
* @author  Darius Rain
* @version 1.1
* @since   20-11-24
*/
public class CardGenerator extends Generator {

    private static final String[] SUITS = {"♢", "♠", "♡", "♣"};
    private static final String[] FACES = {"Jack", "Queen", "King", "Ace"};
    private static final int FACE_VALUES = 13;
    public static final int LOW = 1;
    public static final int HIGH = 10;

    /**
    * Returns a string representing the suit which could be any string in the SUITS array
    * @see Generator#generate(int)
    * @return String
    */
    public static String suit() { return SUITS[generate(SUITS.length) - 1]; }

    /**
    * Could return any face value ranging from (2,3,4,5 ... 10, Jack, Queen, King, Ace) at random.
    * @see Generator#generate(int)
    * @return String
    */
    public static String face() {
        int faceValue = generate(FACE_VALUES);
        if (faceValue <= HIGH && faceValue > LOW) {
            return faceValue + "";
        } else {
            faceValue = generate(FACES.length);
            return FACES[faceValue == FACES.length ? 0 : faceValue];
        }
    }

    /**
    * Returns a random suit with a random face by using the above methods.
    * @see Deck#draw()
    * @return String
    */
    public static String nextCard() {
        return suit() + face();
    }

}
