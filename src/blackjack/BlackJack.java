package blackjack;
import blackjack.materials.Table;
import blackjack.players.Dealer;
import blackjack.players.Player;
import blackjack.utils.UserInteractions.Menu;

/**
* <h1>BlackJack</h1>
*
* <h3>Versions:</h3>
* v0.1 Instantiate a card with a value and suit <br\>
* v0.2 Instantiate a deck and build it with 52 unique cards. <br\>
* v0.3 Draw a card from the deck. <br\>
*     @see blackjack.utils.generators.CardGenerator#nextCard()  <br\>
*     @see blackjack.materials.Deck#draw() <br\>
*
* v0.4 Confirm if I can't draw the same card twice from a deck. (ie the card appears to be removed). <br\>
* v0.5 Detect when the deck is empty. <br\>
* v0.6 Shuffle the order of the deck. <br\>
* v1.0 Draw two cards from the deck into a hand. <br\>
*     @see java.util.Set
*     @see blackjack.materials.Deck#draw()
*
* v1.1 Determine the value of the hand based on the values
* v1.2 Determine the BlackJack value of the hand.
*     @see blackjack.players.Hand#countCards()
*     @see blackjack.players.Hand#hit(java.lang.String, boolean)
*     @see blackjack.utils.UserInteractions.Parser#cardValue(java.lang.String)
*
* v1.3 Draw another card into the hand and determine if a Bust has occurred.
*     @see blackjack.players.Hand#didBust()
*
* v1.4 End my turn.
* v2.0 Have than one player at the same time.
* v2.1 Each player will take their turn.
* v2.2 TODO -> System that will automate the player process following the dealer rules.
* v2.3 Compare the player hand against the dealer hand and determine if the player has won.
*     @see Dealer#dealHits(java.util.LinkedHashMap)
*     @see blackjack.utils.UserInteractions.Menu#choice(java.lang.String)
*
* v2.4 Bet points.
*     @see blackjack.players.Dealer#dealRound(java.util.LinkedHashMap)
*
* v2.5 The end game process will award points.
*     @see blackjack.players.Dealer#dispense(java.util.LinkedHashMap)
*
* Additional challenges:
* TODO
*   v3.0 I can detect BlackJack (face card and an ace)
*   v3.1 if player has BlackJack they gain 2.5x their bet.
*   v3.2 implement the Double feature.
*   v4.0 implement the split feature.

 * </p>
 *
 * @author  Darius Rain
 * @version 2.5
 * @since   2020-11-14
 * @github https://www.github.com/DariusRain/Java-Blackjack
 */
public class BlackJack {

    private Table gameTable = new Table();
    private boolean initialGame = true;


/**
* Runs a loop that calls methods from the table class representing each step
* of the process in a Blackjack game, where Table holds the dealer and the players.
* @see Table
* @see Dealer
 * @see Player
* @return void
*/
    public void start() {
        Menu.welcome();
        // If table is empty or initial game since empty() would be true
        while((!gameTable.empty()) || initialGame) {

            initialGame = false;

            gameTable.loadPlayers();

            gameTable.getDealer().dealRound(gameTable.getPlayers());

            gameTable.getDealer().dealHits(gameTable.getPlayers());

            gameTable.getDealer().dispense(gameTable.getPlayers());
        }
    }


}
