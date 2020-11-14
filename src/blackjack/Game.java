package blackjack;
import blackjack.materials.*;
import blackjack.players.Player;
import blackjack.utils.UserInteractions.Menu;

public class Game {

    private Table gameTable = new Table();
    private boolean initialGame = true;


    public void start() {
        // If table is empty or initial game since empty() would be true
        Menu.blackJack();
        while((!gameTable.empty()) || initialGame) {

            initialGame = false;

            gameTable.newRound();

            gameTable.getDealer().deal(gameTable.getPlayers());

            gameTable.getDealer().dealHits(gameTable.getPlayers());

            gameTable.getDealer().dispense(gameTable.getPlayers());
        }
    }


}
