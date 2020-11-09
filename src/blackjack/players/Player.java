package blackjack.players;

import blackjack.utils.Console;
import blackjack.utils.IdGenerator;
import blackjack.utils.Parser;

import java.util.ArrayList;

public class Player {

    protected int value = 0;
    public String name;
    public String id;
    public int winnings = 0;
    public int bet = 0;
    protected boolean busted = false;
    protected ArrayList<String> cards = new ArrayList<>();
//    protected ArrayList<String> split = new ArrayList<>();
    protected Parser reader = new Parser();
    protected Console console = new Console();

    public Player (String name) {
        this.name = name;
        id = IdGenerator.id();
    }

    public void setBet(int bet) { this.bet = bet; }

    public void hitMe(String card) {

        cards.add(card);


        if (reader.isAce(card)) {
            handleAce();
        }

        if (reader.isFaceCard(card)) {
            value += 10;
        }

        if(reader.isNumber(card)) {
            value += Integer.parseInt(card.split(" ")[0]);
        }


    }

    public boolean hasBusted() {

        if (21 < value) {
            busted = true;
            return true;
        }

        return false;
    }

    public void display() {
        for(String card: cards) {
            System.out.println(card);
        }
    }

    public void handleAce() {
        while(true) {

            String choice = console.input("Count Ace as 11(y) or 1(n)? ");

            if(reader.compare("y\\i", choice)) {
                value += 11;
            }

            if(reader.compare("n\\i", choice)) {
                value += 1;
            }

        }
    }

    public void newDeal() {value = 0; cards = new ArrayList<>();}


}
