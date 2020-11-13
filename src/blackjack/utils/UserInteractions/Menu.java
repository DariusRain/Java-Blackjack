package blackjack.utils.UserInteractions;

import blackjack.players.Player;

import blackjack.utils.UserInteractions.*;

import java.util.*;

public class Menu {

    // Returns false if Y or y is not.
    public static boolean choice(String message) {
        return Parser.compare(Console.input(message + " (Y/N)").toUpperCase().strip().trim(), "^Y$");
    }

    // Returns an invalid message with the invalid input
    public static void invalid(String invalidInput) {Console.log("Invalid Input -> " + "\"" + invalidInput + "\"");}

    // askFor methods Returns any extpected return value user types in, removes whitespace
    public static String askForString(String message) {

        String response = Console.input(message).strip().trim();
        if (response.length() > 0) {
            return response;
        }

        invalid(response);

        return askForString(message);

    }

    public static int askForInt(String message) {
        String response = Console.input(message);
        if(Parser.compare(response, "^[0-9]+$")) {
            return Integer.parseInt(response);
        }
        invalid(response);

        return askForInt(message);
    }

    public static int bet(int playerChips) {
        int bet = askForInt("Bet");
        if (bet <= playerChips) {
            return bet;
        }
        return bet(playerChips);
    }


    public static void displayPlayer(Player user) { Console.log("Player: " + user.name + "Winnings: " + user.winnings()); }

    public static void displayPlayers(ArrayList<Player> players) {
        for (Player user : players) {
            displayPlayer(user);
        }
    }



    // Asks each player for a hit until a false boolean is returned by Menu.choice
    // Runs loop O(lengthOfMap - 1) times.

}
//    Returns false by default.
//    public boolean hitMe() {return reader.compare(Console.input("Hit Y or any key?"), "^Y$"); }