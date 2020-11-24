package blackjack.players;
import blackjack.utils.UserInteractions.BlackJackConsole;
import blackjack.utils.UserInteractions.Parser;
import blackjack.utils.generators.IdGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


/**
* <h1>Player</h1>
* <p>
*     In this class contains a class made to meet a user's expectations during their experience in a virtual or
 *    a blackjack game at a casino or at home.
* </p>
* @author  Darius Rain
* @version 1.1
* @since   20-11-24
*/
public class Player implements Hand, Purchases {

    protected HashMap<String, ArrayList<String>> cards = new HashMap<>();
    protected final static int BLACKJACK = 21;
    protected final static String SPLIT_HAND = "split";
    protected final static String NORMAL_HAND = "normal";
    protected boolean didSplit = false;
    protected boolean busted = false;
    protected boolean blackjack = false;
    protected boolean doubleDown = false;
    protected int normalCardSum = 0;
    protected int splitCardSum = 0;
    protected int chips = 0;
    protected int chipsPurchased = 0;
    public String name;
    public String id;
    protected int bet = 0;

    /**
    * This constructor only takes a name of a player.
     * Every other field above is default/pre-assigned except for the cards field.
     * Note: the cards field instantiates a hashmap of key:string-hand-type value:Arraylist<String>
    * @param name The only parameter expected
    * @see Dealer#dealRound(LinkedHashMap) Only place Player gets instantiated.
    * @return Player
    */
    public Player (String name) {
        this.name = name;
        id = IdGenerator.id(name);
        cards.put(NORMAL_HAND, new ArrayList<>());
        cards.put(SPLIT_HAND, new ArrayList<>());
    }

    /**
    * Player can bet chips
    *  NOTE: Chips are not subtracted from bet during the hit, but the bet field is set.
    * @see Player#bet
    * @param chips Chips to bet
    * @return boolean -> lets calling class know if the bet is valid
    */
    public boolean bet(int chips) {
        if (chips <= this.chips) {
            this.bet = chips;
            return true;
        }

        return false;

    }

    /**
    * Prints player info to the console.
    * @see BlackJackConsole#displayPlayer(String, String, int, int, int, int, boolean, HashMap)
    * @return Nothing
    */
    public void display() { BlackJackConsole.displayPlayer(name, id, chips, winnings(), normalCardSum, splitCardSum, didSplit, cards); }



    public void lost(boolean blackjack) {
        int chipsLost = blackjack ? bet * 4 : bet;
        this.chips -= chipsLost;
    }


    public void win() {
        int chipsWon = blackjack ? bet * 4 : bet;
        this.chips += chipsWon;
    }



    // Purchases Interface ================================================
    /**
    * Returns true or false if player can play based on the chips field.
    * @see Purchases#hasChips()
     * @see Dealer#dispense(LinkedHashMap)
     * @see #chips
    * @return ...
    */
    public boolean hasChips() { return 0 < chips; }

    /**
    * Returns the chips gained or lost by player, by subtracting the amount of chips purchased
    * to the amount chips currently in the player's possession.
    * @see Player#chips
    * @see Player#chipsPurchased
    * @return int The amount of chips player profited
    */
    public int winnings() { return chips - chipsPurchased; }

    /**
    * About ...
    * @param chips
    * @see Purchases#buyIn(int)
    * @return Nothing
    */
    public void buyIn(int chips) {
        this.chipsPurchased += chips;
        this.chips += chips;
        BlackJackConsole.log("Purchased: " + chipsPurchased);
        BlackJackConsole.log(this.name + " now has " + this.chips + " chips...");
    }
    //==============================================================================


    // Hand interface ==============================================================
    /**
    * Adds to normalCardSum or splitCardSum
    * @param card
     * @param isSplit
    * @see Hand#hit(String, boolean)
    * @return Nothing
    */
    public void hit(String card, boolean isSplit) {
        int cardValue = 0;
        this.addCard(card, isSplit);
        if(!isSplit) {
            cardValue = Parser.cardValue(card, this.normalCardSum);
            normalCardSum += cardValue;
        } else {
            cardValue = Parser.cardValue(card, this.splitCardSum);
            splitCardSum += cardValue;
        }

    }


    /**
    * Checks if a player's normal hand sum or if split hand both are over 21
    * @see Hand#didBust()
    * @return boolean
    */
    public boolean didBust() {
        if (BLACKJACK < this.normalCardSum || (this.didSplit && BLACKJACK < this.splitCardSum)) {
            BlackJackConsole.log("Busted!");
            this.busted = true;
            return true;
        }
        return false;
    }

    /**
    * Adds a card to either a player's normal or split hand
    * @param card What gets added to a player or even dealers hand.
     * @param isSplit Determines if card should be added to split hand if so it would be true.
    * @see Hand#addCard(String, boolean)
    * @return ...
    */
    public void addCard(String card, boolean isSplit) { cards.get(isSplit ? "split" : "normal").add(card);}


    /**
     * ( Feature will be added soon! )
     * @see ...
     * @return Nothing
     */
    public void split() { didSplit = true;}

    /**
     * ( Feature will be added soon!)
     * @see ...
     * @return Nothing
     */
    public void doubleDown() { doubleDown = true;}

    /**
     * Clears a player's hand, boolean, integer values for the next round.
     * @see Dealer#dispense(LinkedHashMap)
     * @return Nothing
     */
    public void clear() {
        didSplit = false;
        busted = false;
        blackjack = false;
        doubleDown = false;
        bet=0;
        normalCardSum = 0;
        splitCardSum = 0;
        cards.get("normal").clear();
        cards.get("split").clear();
    }
    //==============================================================================

}


