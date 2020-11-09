package blackjack;

import blackjack.materials.Deck;

public class Game {
    public void start() {
        Deck deck = new Deck();
        deck.shuffle();
        deck.display();
    }
}
