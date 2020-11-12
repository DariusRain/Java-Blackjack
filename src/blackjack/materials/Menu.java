package blackjack.materials;

import blackjack.players.Dealer;
import blackjack.players.Player;
import blackjack.utils.*;

import java.util.*;

public class Menu {
    public static Console console = new Console();
    public static Parser reader = new Parser();

    public static void displayPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            console.log("Player: " + player.name + "Winnings: " + player.winnings);
        }
    }

    // Returns false if Y or y is not.
    public static boolean choice(String message) {
        return reader.compare(console.input(message + " (Y/N)").strip().trim(), "^Y$\\i");
    }

    public static String newPlayer() { return console.input("Enter Name: ").strip().trim(); }


    // Asks each player for a hit until a false boolean is returned by Menu.choice
    // Runs loop O(lengthOfMap - 1) times.

}
//    Returns false by default.
//    public boolean hitMe() {return reader.compare(console.input("Hit Y or any key?"), "^Y$"); }