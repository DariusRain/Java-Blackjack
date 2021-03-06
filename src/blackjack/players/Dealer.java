package blackjack.players;

import blackjack.BlackJack;
import blackjack.materials.Deck;
import blackjack.utils.UserInteractions.BlackJackConsole;
import blackjack.utils.UserInteractions.Menu;
import blackjack.utils.UserInteractions.Console;
import blackjack.utils.UserInteractions.Parser;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* <h1>Dealer</h1>
* <p>
*   This class contains Deck and methods that handle players in the game,
 *   this would be located in the Table class
* </p>
 * @see blackjack.materials.Table#dealer
* @author  Darius Rain
* @version 1.1
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
        return RULE <= normalCardSum || (didSplit && RULE <= splitCardSum);
    }

    /**
    * Takes input and passes the integer as the user's buy in.
    * @param user -> Uses the buy in method since classes only within package.players can call it.
    * @see Player#buyIn(int)
    * @see Menu#askForInt(String)
    * @return ...
    */
    public void getBuyIn(Player user) {
        int numberOfChips = Menu.askForInt("Buy in: $");
        user.buyIn(numberOfChips);
        BlackJackConsole.log("Your ID: " + user.id);
    }

    /**
    * Loops through a linked hashmap of {@code Player} classes expected to be passed as
    * an argument.  Before dealing the first card each player will be prompted for their
    * bet then the dealing continues on.
    * NOTE: Dealer also deals to itself
    * @param players -> {@code LinkedHashMap<String,Player>}
    * @see Player
    * @see LinkedHashMap
     * @see BlackJack#start()
     * @return Nothing
    */
    public void dealRound(LinkedHashMap<String, Player> players) {
        // https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        deck.reset();
        int dealCount = 0;
        while ( dealCount++ < 2 ) {
            // Class made for iterating collections,  similar to Scanner.nextLine()
            Iterator iterator = players.entrySet().iterator();
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
                    Console.log("****** ( Blackjack Bonus ) ******");
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


    /**
    * Iterates through a linked hashmap from the Table class then displays
     * and asks each player if they want to hit. Then the dealer hits himself
     * based on sum being less than 17.
    * @param players Linked hashmap of players to be iterated through.
    * @see blackjack.materials.Table#players
     * @see BlackJack#start()
     * @return Nothing
    */
    public void dealHits(LinkedHashMap<String, Player> players) {

        // https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        Iterator iterator = players.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry obj = (Map.Entry) iterator.next();
            Player onPlayer = (Player) obj.getValue();
            onPlayer.display();

            while(onPlayer.blackjack == false && Menu.choice("Hit")) {
                onPlayer.hit(deck.draw(), false);
                onPlayer.display();
                if (onPlayer.didBust()) {
                    break;
                }
            }


        }
        // Else must be on dealer
        this.display();
        while (true) {
            if (limit()) {
                this.didBust();
                break;
            }
            this.hit(deck.draw(), false);
            this.display();
        }
    }


    /**
    * Iterates through players and add chips or subtract chips bet by each player.  Based on
     * each player's comparison to the dealer.  And for players who got blackjack they get x2.5 and double x2.
    * @param players
    * @see blackjack.materials.Table#players
     * @see BlackJack#start()
    * @return ...
    */
    public void dispense(LinkedHashMap<String, Player> players) {
            Console.clearScreen();
            Console.log("Game Results: ");
            Iterator iterator = players.entrySet().iterator();

            while (iterator.hasNext()) {
                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();


                // If house won
                if ( onPlayer.busted && this.busted == false || this.normalCardSum <= 21 && onPlayer.normalCardSum < this.normalCardSum) {
                    Console.log("\n");
                    Menu.winner(this.name, onPlayer.name, this.normalCardSum, onPlayer.normalCardSum);
                    onPlayer.lost(this.blackjack);
                    Console.log("House won: " + (this.blackjack ? "(BLACKJACK BONUS = " + onPlayer.bet * 4 + " = ( " + onPlayer.bet + " x 4" + " ) )" : onPlayer.bet) + " chips...");
                    Console.log("\n");

                }

                // if player won
                if (this.busted == true && onPlayer.busted == false || onPlayer.normalCardSum <= 21 && this.normalCardSum < onPlayer.normalCardSum ) {
                    Console.log("\n");
                    Menu.winner(onPlayer.name, this.name, onPlayer.normalCardSum, this.normalCardSum);
                    onPlayer.win();
                    Console.log( onPlayer.name + " won: " + (onPlayer.blackjack ? "(BLACKJACK BONUS = " + onPlayer.bet * 4 + " = ( " + onPlayer.bet + " x 4" + " ) )" : onPlayer.bet) + " chips...");
                    Console.log("\n");
                }

                // if tie
                if (this.busted && onPlayer.busted || this.normalCardSum == onPlayer.normalCardSum) {
                    Console.log("\n");
                    Menu.tie(this.name, onPlayer.name, this.normalCardSum, onPlayer.normalCardSum);
                    Console.log("\n");
                }

                // Clear player's fields for new hand
                onPlayer.clear();

            }

            // Like above but for dealer.
            this.clear();
            Console.clearScreen();

    }

}
