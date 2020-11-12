package blackjack.materials;
import blackjack.utils.generators.Generator;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private Set<String> cards = new HashSet<>();
    private List<String> deck = new ArrayList<>();
    private Generator generator = new Generator();

    public void shuffle() {
        cards.clear();
        System.out.println("Shuffling deck...");

        while(cards.size() < 52) {
            cards.add(generator.nextCard());
        }

    }

    public void display() {

        int i = 1;

        for(String card: cards) {
            System.out.println(i++ + ": " + card);
        }

    }

    public String draw(int cardCount) { return deck.get(cardCount); }


    public void setDeck() {
        if (deck.size() > 0) {
            deck.clear();
        }
        deck = cards.stream().collect(Collectors.toList());
    }

    public List<String> getCards() { return deck; }

}


