package blackjack.players;

public class Dealer extends Player {

    public Dealer(String name) {
        super(name);
    }

    public boolean rule() { return 17 <= value; }
}
