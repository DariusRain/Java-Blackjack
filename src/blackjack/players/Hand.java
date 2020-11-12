package blackjack.players;

import blackjack.utils.Console;
import blackjack.utils.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hand {
    HashMap<String, ArrayList<String>> cards = new HashMap<>();
    protected final static int BLACKJACK = 21;
    protected Parser reader = new Parser();
    protected Console console = new Console();
    protected boolean didSplit = false;
    protected int[] cardSum = {0, 0};

    public Hand() {
        cards.put("normal", new ArrayList<>());
        cards.put("split", new ArrayList<>());
    }

    //
    public void addCard(String card, boolean isSplit) { cards.get(isSplit ? "split" : "normal").add(card); }

    public void clear() {
        cards.get("normal").clear();
        cards.get("split").clear();
    }

    public void hit(String card, boolean isSplit) {

        addCard(card, isSplit);


        if (reader.isAce(card)) {
            cardSum[isSplit ? 1: 0] += reader.handleAce();
        }

        if (reader.isFace(card)) {
            cardSum[isSplit ? 1 : 0] += 10;
        }

        if(reader.isNumber(card)) {
            cardSum[isSplit ? 1 : 0] += Integer.parseInt(card.split(" ")[0]);
        }


    }

    public boolean didBust() {
        if (BLACKJACK < cardSum[0] || (didSplit && BLACKJACK < cardSum[1])) {
            return true;
        }
        return false;
    }

//    public void display() {
//            // https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
//            Iterator iterator = cards.keySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry obj = (Map.Entry)iterator.next();
//
//
//
//            }
//    }

    public void split() { didSplit = true; }
}
