package blackjack.players;
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
    protected int chipsBought = 0;
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


    public void display() {
        Console.clearScreen();
        Console.log("( " + name + " ) " +"Chips: " + chips + " Winnings: " + winnings());
        displayHand();
    }

    public boolean canPlay() { return chips > 0; }

    public int winnings() { return chips - chipsBought; }

    public void clear() {
        busted = false;
        blackjack = false;
        bet=0;
        this.clearHand();
    }

    protected void buyIn(int chips) { this.chips += chips; }

    public void split() { didSplit = true;}

    public void lost(boolean blackjack) { this.chips = blackjack ? chips - bet * 4 : chips - bet; }

    public void win() { this.chips = blackjack ? chips + bet * 4 : chips + bet; }

    public void addCard(String card, boolean isSplit) { cards.get(isSplit ? "split" : "normal").add(card);}

    public void clearHand() {
        normalCardSum = 0;
        splitCardSum = 0;
        cards.get("normal").clear();
        cards.get("split").clear();
    }

    public void hit(String card, boolean isSplit) {
//        toString();
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
            Console.log("Busted!");
            this.busted = true;
            return true;
        }
        return false;
    }

    public void displayHand() {
        Console.logf("Hand: ");
        for(String card: cards.get("normal")) {
            Console.logf(card + " ");
        }

        Console.log("\nTotal: " + normalCardSum);

        if (didSplit) {
            Console.logf("Split hand:");
            for (String card: cards.get("split")) {
                Console.logf(card + " ");
            }
            Console.log("Split Total: " + splitCardSum);
        }
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

    @Override
    public String toString() {
        return "Player{" +
                "cards=" + cards +
                ", didSplit=" + didSplit +
                ", busted=" + busted +
                ", normalCardSum=" + normalCardSum +
                ", splitCardSum=" + splitCardSum +
                ", chips=" + chips +
                ", chipsBought=" + chipsBought +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", bet=" + bet +
                '}';
    }
}


