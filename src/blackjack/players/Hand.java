package blackjack.players;
import blackjack.utils.UserInteractions.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hand {
    protected HashMap<String, ArrayList<String>> cards = new HashMap<>();
    protected final static int BLACKJACK = 21;
    protected final static String SPLIT_HAND = "split";
    protected final static String NORMAL_HAND = "normal";
    protected boolean didSplit = false;
    protected boolean busted = true;
    protected int normalCardSum = 0;
    protected int splitCardSum = 0;

    public Hand() {
        cards.put(NORMAL_HAND, new ArrayList<>());
        cards.put(SPLIT_HAND, new ArrayList<>());
    }

    //
    public void addCard(String card, boolean isSplit) { cards.get(isSplit ? "split" : "normal").add(card); }

    public void clearHand() {
        cards.get("normal").clear();
        cards.get("split").clear();
    }

    public void hit(String card, boolean isSplit) {
        int cardValue = Parser.cardValue(card);
        System.out.println("Card: " + card + " = " +  cardValue);
        if(!isSplit) {
            normalCardSum += cardValue;
        } else {
            splitCardSum += cardValue;
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
        if (BLACKJACK < normalCardSum|| (didSplit && BLACKJACK < splitCardSum)) {
            Console.log("Busted!");
            busted = true;
            return true;
        }
        return false;
    }

    public void displayHand() {
        Console.log("Hand: ");
        for(String card: cards.get("normal")) {
            Console.log(card);
        }

        Console.log("Total: " + normalCardSum);

        if (didSplit) {
            Console.log("Split hand:");
            for (String card: cards.get("split")) {
                Console.log(card);
            }
            Console.log("Split Total: " + splitCardSum);
        }
    }




    public void scan() {
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
//        while(it.hasNext()) {
//            Map.Entry cardList = (Map.Entry) it.next();
//            Iterator it2 = cardList.ente
//            for(String card: (ArrayList<String>)cardList) {
//                if(handCount == 1 && didSplit) {
//                    hit(card, true);
//                } else {
//                    hit(card, false);
//                }
//            }
//            handCount += 1;
//        }


    public void split() { didSplit = true; }

//    public void
}
