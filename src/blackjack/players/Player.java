package blackjack.players;

import blackjack.utils.Console;
import blackjack.utils.generators.IdGenerator;
import blackjack.utils.Parser;

public class Player extends Hand {

    protected int chips = 0;
    protected int chipsBought = 0;
    public String name;
    public String id;
    public int bet = 0;

//    protected Hand playerHand = new Hand();
//    protected ArrayList<String> split = new ArrayList<>();

    public Player (String name) {
        this.name = name;
        id = IdGenerator.id(name);
    }

    protected boolean bet(int chips) {
        if (this.chips <= chips) {
            bet = chips;
            return true;
        }
        return false;
    }


//    public void display() { hand.display(); }



    public void clear() {bet=0;}

    public boolean canPlay() {return chips > 0;}

    public int winnings() {return chips - chipsBought; }

    public void split() { didSplit = true;}


}


