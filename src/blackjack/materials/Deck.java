package blackjack.materials;
import blackjack.players.Hand;
import blackjack.utils.UserInteractions.Console;
import blackjack.utils.generators.CardGenerator;
import java.util.*;
import java.util.stream.Collectors;

public class Deck {

    //  A container to hold unique values pf cards, see "draw()" for where this
    private Set<String> cards = new HashSet<>();
    private int lastSize = 0;

    public String draw() {
        lastSize = cards.size() * 1;
        String card = CardGenerator.nextCard();
        cards.add(card);
        if (lastSize == cards.size()) { return draw(); }
        return card;
    }


    public void reset() {
        cards.clear();
        lastSize = 0;
    }

}


