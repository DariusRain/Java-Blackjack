package blackjack.materials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Hand {
    HashMap<String, ArrayList<String>> cards = new HashMap<>();
    public boolean didSplit = false;

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
