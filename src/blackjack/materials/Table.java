package blackjack.materials;

import blackjack.players.Player;

import java.util.HashMap;

public class Table {
    HashMap<String, Player> players =  new HashMap<>();


    public void newPlayer(Player p) {
        players.put(p.name + "-" + p.id, p);
    }
}
