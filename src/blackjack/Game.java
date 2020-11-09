package blackjack;

import blackjack.utils.Generator;

public class Game {
    public void start() {
        Deck deck = new Deck();
        deck.shuffle();
        deck.display();
    }
}
