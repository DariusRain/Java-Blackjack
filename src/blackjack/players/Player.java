package blackjack.players;
import blackjack.BlackJack;
import blackjack.utils.UserInteractions.BlackJackConsole;
import blackjack.utils.UserInteractions.Console;
import blackjack.utils.UserInteractions.Parser;
import blackjack.utils.generators.IdGenerator;

import java.util.ArrayList;
import java.util.HashMap;

public class Player implements Hand {

    protected HashMap<String, ArrayList<String>> cards = new HashMap<>();
    protected final static int BLACKJACK = 21;
    protected final static String SPLIT_HAND = "split";
    protected final static String NORMAL_HAND = "normal";
    protected boolean didSplit = false;
    protected boolean busted = false;
    protected boolean blackjack = false;
    protected int normalCardSum = 0;
    protected int splitCardSum = 0;
    protected int chips = 0;
    protected int chipsPurchased = 0;
    public String name;
    public String id;
    public int bet = 0;

//    protected Hand playerHand = new Hand();
//    protected ArrayList<String> split = new ArrayList<>();

    public Player (String name) {
        this.name = name;
        id = IdGenerator.id(name);
        cards.put(NORMAL_HAND, new ArrayList<>());
        cards.put(SPLIT_HAND, new ArrayList<>());
    }

    /**
    * Player can bet chips
    *  NOTE: Chips are not subtracted from bet during thi
    * @see Player#bet
    * @param chips Chips to bet
    * @return boolean -> lets calling class know if the bet is valid
    */
    protected boolean bet(int chips) {

        if (this.chips <= chips) {
            bet = chips;
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


    public boolean canPlay() { return chips > 0; }

    /**
    * Returns the chips gained or lost by player, by subtracting the amount of chips purchased
    * to the amount chips currently in the player's possession.
    * @see Player#chips
    * @see Player#chipsPurchased
    * @return int The amount of chips player profited
    */
    public int winnings() { return chips - chipsPurchased; }


    /**
    * Clears a player's hand, boolean, integer values for the next round.
     * @return Nothing
    */
    public void clear() {
        busted = false;
        blackjack = false;
        bet=0;
        normalCardSum = 0;
        splitCardSum = 0;
        cards.get("normal").clear();
        cards.get("split").clear();
    }


    protected void buyIn(int chips) {
        this.chipsPurchased += chips;
        this.chips += chips;
        BlackJackConsole.log("Purchased: " + chipsPurchased);
        BlackJackConsole.log(this.name + " now has " + this.chips + " chips...");
    }

    public void split() { didSplit = true;}

    public void lost(boolean blackjack) { this.chips = blackjack ? chips - bet * 4 : chips - bet; }

    public void win() { this.chips = blackjack ? chips + bet * 4 : chips + bet; }

    public void addCard(String card, boolean isSplit) { cards.get(isSplit ? "split" : "normal").add(card);}

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


    public boolean didBust() {
        if (BLACKJACK < this.normalCardSum || (this.didSplit && BLACKJACK < this.splitCardSum)) {
            BlackJackConsole.log("Busted!");
            this.busted = true;
            return true;
        }
        return false;
    }

    public void countCards() {
        normalCardSum = 0;
        splitCardSum = 0;
        cards.forEach((key, value) -> {
            for (String card : value) {
                if (key.equals(SPLIT_HAND)) {
                    hit(card, true);
                }
                if (key.equals(NORMAL_HAND)) {
                    hit(card, false);
                }
            }
        });
    }

}


