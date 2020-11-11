package blackjack.materials;
import blackjack.utils.generators.Generator;

import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

    private Set<String> cards = new HashSet<>();
    private List<String> deck;
    private Generator generator = new Generator();
    int cardCount = 0;

    public void shuffle() {

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

    public String[] draw(int amount) {
        String[] cards = new String[amount - 1];
        int count = 0;
        while(count++ < amount) {
            checkCardCount();
            cards[count++] = deck.get(cardCount);
        }
        return cards;
    }

    public void checkCardCount() {
        if(deck.size() <= cardCount) {
            cardCount = 0;
            shuffle();
            setDeck();
        }
    }

    public void setDeck() { deck = cards.stream().collect(Collectors.toList()); }

}


