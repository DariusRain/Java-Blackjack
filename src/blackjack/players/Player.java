package blackjack.players;

import blackjack.materials.Hand;
import blackjack.materials.Menu;
import blackjack.utils.Console;
import blackjack.utils.generators.IdGenerator;
import blackjack.utils.Parser;

import java.util.ArrayList;

public class Player {

    protected int cardSum = 0;
    protected int chips = 0;
    public String name;
    public String id;
    public int winnings = 0;
    public int bet = 0;

    protected Hand playerHand = new Hand();
//    protected ArrayList<String> split = new ArrayList<>();
    protected Parser reader = new Parser();
    protected Console console = new Console();

    public Player (String name) {
        this.name = name;
        id = IdGenerator.id(name);
    }

    protected boolean bet(int chips) {
        if (this.chips <= chips) {
            this.bet = chips;
            return true;
        }
        return false;
    }

    public void hit(String card) {

        playerHand.addCard(card, false);


        if (reader.isAce(card)) {
            cardSum += reader.handleAce();
        }

        if (reader.isFace(card)) {
            cardSum += 10;
        }

        if(reader.isNumber(card)) {
            cardSum += Integer.parseInt(card.split(" ")[0]);
        }


    }

    public boolean didBust() {

        if (21 < cardSum) {
            return true;
        }

        return false;
    }

//    public void display() { hand.display(); }



    public void newDeal() {cardSum = 0; playerHand.clear();}

    public boolean canPlay() {return chips > 0;}

}
