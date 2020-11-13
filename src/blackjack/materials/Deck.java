package blackjack.materials;
import blackjack.utils.generators.Generator;

import java.util.*;
import java.util.stream.Collectors;

public class Deck {

    private Set<String> cards = new HashSet<>();
    private List<String> deck = new ArrayList<>();

    public void shuffle() {

        System.out.println("Shuffling deck...");
        Collections.shuffle(deck);

    }

    public void init() {
        cards.clear();
        while(cards.size() < 52) {
            cards.add(Generator.nextCard());
        }

    }

//    public void display() {
//
//        int i = 1;
//
//        for(String card: cards) {
//            System.out.println(i++ + ": " + card);
//        }
//
//    }

    public String draw(int cardCount) { return deck.get(cardCount); }


    public void setDeck() {
        if (deck.size() > 0) {
            deck.clear();
        }
        deck = cards.stream().collect(Collectors.toList());
    }

    public List<String> getCards() { return deck; }

}


