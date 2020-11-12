package blackjack;
import blackjack.materials.*;

public class Game {
    private Table gameTable = new Table();
//    private Menu gameMenu = new Menu();
    private int onMenu = 0;

    public void next() {

    }

    // Menus
    // 1: Ask for player(s) (Table.addPlayer(name))
    // 2: Ask to hit (for player in Table.players)
    // 3: Game is over hen players leave the table.


    public void start() {
        while(onMenu < 3) {

            if(onMenu == 1) {

            }

            if (onMenu == 2) {

            }



        }
    }

    public void testDeck() {
        Deck deck = new Deck();
        deck.shuffle();
        deck.display();
    }
}
