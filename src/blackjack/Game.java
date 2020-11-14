package blackjack;
import blackjack.materials.Table;
import blackjack.utils.UserInteractions.Menu;


public class Game {

    private Table gameTable = new Table();
    private boolean initialGame = true;

   /***
    *
    *       Runs a loop that calls methods from the table class representing each
    *   step of the process in a Blackjack game, where Table holds the dealer
    *   and the players.
    *
    * @see blackjack.materials.Table
    * @see blackjack.players.Dealer
    * @return void
    * */
    public void start() {
        Menu.blackJack();
        // If table is empty or initial game since empty() would be true
        while((!gameTable.empty()) || initialGame) {

            initialGame = false;

            gameTable.newRound();

            gameTable.getDealer().dealRound(gameTable.getPlayers());

            gameTable.getDealer().dealHits(gameTable.getPlayers());

            gameTable.getDealer().dispense(gameTable.getPlayers());
        }
    }


}
