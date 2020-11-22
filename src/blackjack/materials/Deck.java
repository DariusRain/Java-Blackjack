package blackjack.materials;
import blackjack.utils.UserInteractions.BlackJackConsole;
import blackjack.utils.UserInteractions.Console;
import blackjack.utils.generators.CardGenerator;
import java.util.*;

/**
* <h1>Deck</h1>
* <p>
*       Contains a set that holds randomly generated cards using
*   when the {@code Deck#draw()} method gets called.  So deck has no cards initially but adds them throughout
*   the game with no risk of duplicates because of the nature of a {@code Set}.
* </p>
* <b>Note:</b> Giving proper comments in your program makes it more
* user friendly and it is assumed as a high quality code.
*
* @author  Darius Rain
* @version 1.0
* @since   2020-11-14
*/
public class Deck {

    //  A container to hold unique values pf cards, see "draw()" for where this
    private Set<String> cards = new HashSet<>();

/**
* Returns recursively until card is not a duplicate based on the size of the Set.
* @params Unused
* @return String -> Returns a unique card
*/
    public String draw() {
        int lastSize = cards.size() * 1;
        String card = CardGenerator.nextCard();
        BlackJackConsole.log("Draw: " + card);
        cards.add(card);
        if (lastSize == cards.size()) { return draw(); }
        return card;
    }


/**
* Clears out Set for a new round of blackjack.
* @params Unused
* @return Nothing
*/
    public void reset() {
        cards.clear();
    }



public void test() {
        while(cards.size() < 52) {
            Console.logf("*");
            draw();
        }
        Console.log(cards.toString());
}
}


