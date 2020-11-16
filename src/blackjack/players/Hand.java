package blackjack.players;

import blackjack.utils.UserInteractions.Console;
import blackjack.utils.UserInteractions.Parser;

public interface Hand {
    /**
    * Adds card to either split or normal arraylist
    * @param card -> Card to be added to arraylist
    * @see Player#cards
    * @return ...
    */
    void addCard(String card, boolean isSplit);



    void clearHand();


    void hit(String card, boolean isSplit);
    boolean didBust();
    void displayHand();
    void countCards();
    void split();

}
