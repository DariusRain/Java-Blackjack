package blackjack;
import blackjack.materials.*;
import blackjack.players.Player;
import blackjack.utils.UserInteractions.Menu;

public class Game {
    private Table gameTable = new Table();
    private boolean initalGame = true;
//    private Menu gameMenu = new Menu();
//    private int onMenu = 0;

//    public void next() {
//        while()
//        gameTable.getDealer();
//    }

    // Menus
    // 1: Ask for player(s) (Table.addPlayer(name))
    // 2: Ask to hit (for player in Table.players)
    // 3: Game is over hen players leave the table.


    public void start() {
        // If table is empty or initial game since empty() would be true
        while((!gameTable.empty()) || initalGame) {

            initalGame = false;

            gameTable.newRound();

            gameTable.getDealer().deal(gameTable.getPlayers());

            gameTable.getDealer().dealHits(gameTable.getPlayers());

        }
    }

//    public void testDeck() {
//        Deck deck = new Deck();
//        deck.shuffle();
//        deck.display();
//    }
}
