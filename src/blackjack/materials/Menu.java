package blackjack.materials;

import blackjack.players.Player;
import blackjack.utils.*;
import java.util.ArrayList;

public class Menu {
    Console console = new Console();
    Parser reader = new Parser();

    public void displayPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            console.log("Player: " + player.name + "Winnings: " + player.winnings);
        }
    }

    public boolean choice(String message) {
        return reader.compare(console.input(message + "Y/N").strip().trim(), "^Y$//i");
    }

    public String newPlayer() {
        return console.input("Enter Name: ").strip().trim();
    }
}
//    Returns false by default.
//    public boolean hitMe() {return reader.compare(console.input("Hit Y or any key?"), "^Y$"); }