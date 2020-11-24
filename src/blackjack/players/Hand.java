package blackjack.players;

import java.util.LinkedHashMap;

/**
* <h1>Hand</h1>
* <p>
*     This interface contains all the methods needed to handle a player's or even dealers hand
 *     contains methods for adding cards to players hand, adding to players total sum and seeing if the player busted.
 *     as well as many more helpers for the process checkout the comments below to learn more.
* </p>
 * @see Dealer
 * @see Player
* @author  Darius Rain
* @version 1.1
* @since   20-11-23
*/
public interface Hand {

    /**
    * Adds card to either split or normal arraylist
    * @param card -> Card to be added to arraylist
     * @see Player#addCard(String, boolean)
     * @see Player#cards
    * @return Nothing
    */
    void addCard(String card, boolean isSplit);

    /**
    * Clears hand for a new deal.
    * @see Player#clear()
    * @return Nothing
    */
    void clear();

    /**
    * Adds to a either a player's normalCardSum or splitCardSum where the first argument is the card
     * and the second boolean if it is the split hand that is taking hit.
    * @param card The card that gets returned from the draw() method.
     * @param isSplit To check if this card drawn should be added to the split hand's sum.
    * @see Player#hit(String, boolean)
    * @return Nothing
    */
    void hit(String card, boolean isSplit);

    /**
    * Checks if a player's total sum of cards is greater than 21, also checks split hand which will be true
     * only if both sums are greater than 21
    * @see Player#didBust()
    * @return boolean
    */
    boolean didBust();

    /**
    * Displays a player's hand and total sum of cards
     * Note: Dealer implements all of the above too.
    * @see Player#display()
    * @return Nothing
    */
    void display();


    void split();

    /**
     * ( Feature still needs added )
     * @see ...
     * @return Nothing
     */
    void doubleDown();

}
