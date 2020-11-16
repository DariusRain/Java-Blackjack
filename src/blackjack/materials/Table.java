package blackjack.materials;

import blackjack.players.Dealer;
import blackjack.players.Player;
import blackjack.utils.UserInteractions.Menu;
import blackjack.utils.generators.IdGenerator;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
* <h1>Table</h1>
* <p>
*     Holds list of players, a dealer and methods that performs operations on them (i.e players, dealer).
* </p>
* @see Dealer
* @see Player
* @author  Darius Rain
* @version 1.0
* @since   2020-11-14
*/

public class Table {

    private LinkedHashMap<String, Player> players = new LinkedHashMap<>();
    private Dealer dealer = new Dealer("House");
    private String[] order;
    private int onPlayer = 0;
    private int chips = 0;
    public static final int MAX = 7;


    /**
    * Adds new player to players hashmap.
    * @param user -> Player Object that gets added to the players hashmap.
    * @see Player
    * @return Nothing
    */
    public void newPlayer(Player user) { players.put(IdGenerator.toKey(user.name, user.id), user); }


    /**
    * Removes player from hashmap that is no longer playing.
    * @see Player
    * @return Nothing
    */
    public void removePlayers() {
        Iterator iterator = players.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry obj = (Map.Entry) iterator.next();
            Player onPlayer = (Player) obj.getValue();

            if (!onPlayer.canPlay()) {
                players.remove(IdGenerator.toKey(onPlayer.name, onPlayer.id));
            }

        }
    }

    /**
    * Runs a loop until either table is full or user rejects going further answering (y/n)
    * @see Menu#choice(String)
    * @return Nothing
    */
    public void loadPlayers() {
        onPlayer = 0;
        // Take in new players if table is not full and Yes is answered.
        Menu.newRound();
        while (Menu.choice("New player") && !full()) {
            String playerName = Menu.askForString("Player name: ");
            Player user = new Player(playerName);
            dealer.getBuyIn(user);
            newPlayer(user);
        }

    }


    /**
    * Returns the dealer field from the Table class.
    * @see Table#dealer
    * @see Dealer
    * @return Dealer
    */
    public Dealer getDealer() { return dealer; }

    /**
    * Returns the players field from the Table class
    * @see Table#players
    * @see LinkedHashMap
    * @return LinkedHashMap<String, Player>
    */
    public LinkedHashMap<String, Player> getPlayers() { return players; }


    /**
    * Checks if table is empty by checking players size.
    * @see Table#players
    * @return boolean
    */
    public boolean empty() { return players.size() == 0; }

    /**
     * Checks if table is full by checking players size.
     * @see Table#players
     * @return boolean
     */
    public boolean full() { return MAX < players.size(); }



}


//  Possible deprecated code:

//    public boolean onDealer() { return order.length <= onPlayer ? true : false; }

//    public void setOrder() { order = players.keySet().toArray(new String[players.size()]); }

//    public Player getPlayer(Player user) { return players.get(IdGenerator.toKey(user.name, user.id)); }

//    public Player nextPlayer() { return players.get(order[onPlayer++]); }
