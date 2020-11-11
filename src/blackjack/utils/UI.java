package blackjack.utils;

import blackjack.materials.Menu;
import blackjack.players.Player;
import java.util.ArrayList;

public interface UI {

    void displayPlayers(ArrayList<Player> players);

    boolean choice();

    public boolean choice(String message);


    public String newPlayer();

}
