package blackjack.players;
import blackjack.utils.UserInteractions.Console;
import blackjack.utils.generators.IdGenerator;

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

    public void display() {
        Console.clearScreen();
        Console.log("( " + name + " ) " +"Chips: " + chips + " Winnings: " + winnings());
        displayHand();
    }

    public boolean canPlay() { return chips > 0; }

    public int winnings() { return (chipsBought - chips); }

    public void clear() {
        busted = false;
        bet=0;
        this.clearHand();
    }

    public void buyIn(int chips) { this.chips += chips; }

    public void split() { didSplit = true;}

    public void lost() { chips -= bet; }

    public void win() { chips += bet; }

}


