package blackjack.materials;

import blackjack.players.Dealer;
import blackjack.players.Player;
import blackjack.utils.UserInteractions.Menu;
import blackjack.utils.generators.IdGenerator;
import java.util.LinkedHashMap;

public class Table {

    private LinkedHashMap<String, Player> players = new LinkedHashMap<>();
    private Dealer dealer = new Dealer("House");
    private String[] order;
    private int onPlayer = 0;
    private int chips = 0;
    public static final int MAX = 7;

    public void newPlayer(Player user) { players.put(IdGenerator.toKey(user.name, user.id), user); }

    public void removePlayer(Player user) { players.remove(IdGenerator.toKey(user.name, user.id)); }

    public void newRound() {
        onPlayer = 0;
        // Take in new players if table is not full and Yes is answered.
        while (Menu.choice("New player?") && !full()) {
            int numberOfChips = Menu.askForInt("Buy in: $");
            String playerName = Menu.askForString("Player name:");
            Player user = new Player(playerName);
            user.buyIn(numberOfChips);
            newPlayer(user);
        }

    }

    public void setOrder() { order = players.keySet().toArray(new String[players.size()]); }

    public Player getPlayer(Player user) { return players.get(IdGenerator.toKey(user.name, user.id)); }

    public Player nextPlayer() { return players.get(order[onPlayer++]); }

    public Dealer getDealer() { return dealer; }

    public LinkedHashMap<String, Player> getPlayers() { return players; }

    public boolean onDealer() { return order.length <= onPlayer ? true : false; }

    public boolean empty() { return players.size() == 0; }

    public boolean full() { return MAX < players.size(); }



}
