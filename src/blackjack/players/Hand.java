package blackjack.players;
import blackjack.utils.UserInteractions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hand {
    protected HashMap<String, ArrayList<String>> cards = new HashMap<>();
    protected final static int BLACKJACK = 21;
//    protected Parser reader = new Parser();
    protected boolean didSplit = false;
    protected int[] cardSum = {0, 0};

    public Hand() {
        cards.put("normal", new ArrayList<>());
        cards.put("split", new ArrayList<>());
    }

    //
    public void addCard(String card, boolean isSplit) { cards.get(isSplit ? "split" : "normal").add(card); }

    public void clearHand() {
        cards.get("normal").clear();
        cards.get("split").clear();
    }

    public void hit(String card, boolean isSplit) {

        addCard(card, isSplit);

        if (Parser.isAce(card)) {
            cardSum[isSplit ? 1: 0] += Parser.handleAce();
        }

        if (Parser.isFace(card)) {
            cardSum[isSplit ? 1 : 0] += 10;
        }

        if(Parser.isNumber(card)) {
            cardSum[isSplit ? 1 : 0] += Integer.parseInt(card.split(" ")[0]);
        }


    }

// TODO: add a override to hit method to take in intial deal and handle aces
//    public void hit(String card, boolean isSplit) {
//
//        addCard(card, isSplit);
//
//        if (Parser.isAce(card)) {
//            cardSum[isSplit ? 1: 0] += Parser.handleAce();
//        }
//
//        if (Parser.isFace(card)) {
//            cardSum[isSplit ? 1 : 0] += 10;
//        }
//
//        if(Parser.isNumber(card)) {
//            cardSum[isSplit ? 1 : 0] += Integer.parseInt(card.split(" ")[0]);
//        }
//
//
//    }

    public boolean didBust() {
        if (BLACKJACK < cardSum[0] || (didSplit && BLACKJACK < cardSum[1])) {
            Console.log("Busted!");
            return true;
        }
        return false;
    }

    public void displayHand() {
        Console.log("Hand: ");
        for(String card: cards.get("normal")) {
            Console.log(card);
        }

        Console.log("Total: " + cardSum[0]);

        if (didSplit) {
            Console.log("Split hand:");
            for (String card: cards.get("split")) {
                Console.log(card);
            }
            Console.log("Split Total: " + cardSum[0]);
        }
    }

    public void split() { didSplit = true; }

}
