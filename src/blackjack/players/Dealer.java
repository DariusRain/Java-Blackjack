package blackjack.players;

import blackjack.materials.Deck;
import blackjack.utils.UserInteractions.Menu;
import blackjack.utils.UserInteractions.Console;
import blackjack.utils.UserInteractions.Parser;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* <h1>Dealer</h1>
* <p>
*   This class contains Deck
* </p>
* @author  Darius Rain
* @version 1.0
* @since   20-11-14
*/
public class Dealer extends Player implements Hand {

    private Deck deck = new Deck();
    private static final int RULE = 17;

    public Dealer(String name) {
        super(name);
    }

    /**
    * Checks if dealer has exceeded the limit of 17
    * @see Dealer#RULE
    * @return ...
    */
    public boolean limit() {
        return RULE < normalCardSum || (didSplit && RULE < splitCardSum);
    }

    /**
    * Takes input and passes the integer as the user's buy in.
    * NOTE: Dealer can only call the player's buy in method.
    * @param user -> Uses the buy in method since classes only within package.players can call it.
    * @see Player#buyIn(int)
    * @see Menu#askForInt(String)
    * @return ...
    */
    public void getBuyIn(Player user) {
        int numberOfChips = Menu.askForInt("Buy in: $");
        user.buyIn(numberOfChips);
    }


    /**
    * Loops through a linked hashmap of {@code Player} classes expected to be passed as
    * an argument.  During each iteration of the first loop a nested loop deals to players and after that loop stops
    * @param players -> {@code LinkedHashMap<String,Player>}
    * @see Player
     * @see LinkedHashMap
    * @return Nothing
    */
    // loops through O(size * 2) and deals the initial deal before game starts.
    public void dealRound(LinkedHashMap<String, Player> players) {
        // https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        int dealCount = 0;
        while ( dealCount++ < 2 ) {
            // Class made for iterating collections,  similar to Scanner.nextLine()
            Iterator iterator = players.entrySet().iterator();
            Console.log("Deal count: " + dealCount);

            while (iterator.hasNext()) {
                // This returns the key and value in object.
                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();

                if (dealCount == 1) {
                    onPlayer.display();
                    int numberOfChips = Menu.bet(onPlayer.chips);
                    onPlayer.bet(numberOfChips);
                }

                onPlayer.hit(deck.draw(), false);
                if (Parser.isBlackJack(onPlayer.normalCardSum)) {
                    onPlayer.blackjack = true;
                    Console.logf(onPlayer.name + " -> ");
                    Menu.blackJack();
                }
            }

            // This is the the Dealer's hand
            this.hit(deck.draw(), false);
            if (Parser.isBlackJack(this.normalCardSum)) {
                this.blackjack = true;
                Console.logf(this.name + " -> ");
                Menu.blackJack();
            }

        }
    }

    // loops through O((playersSize + playerHits) + dealerHits) times and asks a player for a hit until player says no.
    public void dealHits(LinkedHashMap<String, Player> players) {

        // https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        Iterator iterator = players.entrySet().iterator();

        while(true) {

            // If there is another player
            if(iterator.hasNext()) {

                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();
//                onPlayer.countCards();
                onPlayer.display();
                while((!(this.blackjack || onPlayer.blackjack || onPlayer.normalCardSum == BLACKJACK)) && Menu.choice("Hit") ) {
//                    onPlayer.countCards();
                    onPlayer.display();
                    onPlayer.hit(deck.draw(), false);
                    if(onPlayer.didBust()) {
                        break;
                    }

                }

            }

            // Else must be on dealer
            else {
                this.display();
                while (true) {
                    this.hit(deck.draw(), false);
                    this.display();
                    if(limit()) {
                        this.didBust();
                        break;
                    }
                }
                break;
            }

        }

    }

    public void dispense(LinkedHashMap<String, Player> players) {
            Iterator iterator = players.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();


//                Menu.result(this.name, this.normalCardSum, onPlayer.name, onPlayer.normalCardSum);
                // If dealer won
                if ( onPlayer.busted && !this.busted || onPlayer.normalCardSum < this.normalCardSum) {
                    Menu.winner(this.name, onPlayer.name, this.normalCardSum, onPlayer.normalCardSum);
                    onPlayer.lost(this.blackjack);
                    Console.log("Dealer won: " + onPlayer.chips + "");

                }
                // if player won
                if (this.busted && !onPlayer.busted || this.normalCardSum < onPlayer.normalCardSum ) {
                    Menu.winner(onPlayer.name, this.name, onPlayer.normalCardSum, this.normalCardSum);
                    onPlayer.win();
                    Console.log(onPlayer.name + "won" + onPlayer.chips + "");
                }

                // if tie
                if (!this.busted && !onPlayer.busted || this.normalCardSum == onPlayer.normalCardSum) {
                    Menu.tie(this.name, onPlayer.name, this.normalCardSum, onPlayer.normalCardSum);
                }

                // Clear player's fields for new hand
                onPlayer.clear();

            }
            // Like above but for dealer.
            this.clear();
        }

}
