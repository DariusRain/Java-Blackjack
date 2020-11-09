package blackjack.materials;
import blackjack.utils.Generator;

import java.util.HashSet;
import java.util.Set;
public class Deck {

    private Set<String> cards = new HashSet<>();
    private Generator generator = new Generator();

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

}
