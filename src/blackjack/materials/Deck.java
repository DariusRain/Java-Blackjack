package blackjack.materials;
import blackjack.utils.UserInteractions.Console;
import blackjack.utils.generators.CardGenerator;
import java.util.*;
import java.util.stream.Collectors;

public class Deck {

    //  A container to hold unique values pf cards, see "init()" for where this
    private Set<String> cards = new HashSet<>();
    private List<String> deck = new ArrayList<>();

    public void shuffle() {

        System.out.println("Shuffling deck...");
        Collections.shuffle(deck);

    }

    public void init() {
        Console.log("Initializing game...");
        cards.clear();
        while(cards.size() < 52) {
            int last = cards.size();
            cards.add(CardGenerator.nextCard());
            if(last < cards.size()) {
                Console.logf(CardGenerator.suit() + "-");
            }
        }
        Console.log(CardGenerator.suit());

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


