package blackjack.materials;

import blackjack.players.Dealer;
import blackjack.players.Player;
import blackjack.utils.generators.IdGenerator;
import java.util.LinkedHashMap;

public class Table {

    private LinkedHashMap<String, Player> players = new LinkedHashMap<>();
    private Dealer dealer = new Dealer("House");
    private String[] order;
    private int onPlayer = 0;
    private int chips = 0;

    public void newPlayer(Player user) { players.put(IdGenerator.toKey(user.name, user.id), user); }

    public void removePlayer(Player user) { players.remove(IdGenerator.toKey(user.name, user.id)); }

    public Player getPlayer(Player user) { return players.get(IdGenerator.toKey(user.name, user.id)); }

    public Player nextPlayer() { return players.get(order[onPlayer++]); }

    public Dealer getDealer() {return dealer;}

    public void setOrder() { order = players.keySet().toArray(new String[players.size()]);}

    public boolean onDealer() {return order.length <= onPlayer ? true : false;}

    public void newRound() { onPlayer = 0; }

}
