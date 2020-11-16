package blackjack.utils.UserInteractions;

import blackjack.players.Dealer;
import blackjack.players.Player;

import blackjack.utils.UserInteractions.*;

import java.util.*;

public class Menu {

    // Returns false if Y or y is not.
    public static boolean choice(String message) {
        return Parser.compare(Console.input(message + " (Y/N): ").toUpperCase().strip().trim(), "^Y$");
    }

    // Returns an invalid message with the invalid input
    public static void invalid(String invalidInput) {
        Console.log("Invalid Input -> " + "\"" + invalidInput + "\"");
    }

    // askFor methods Returns any extpected return value user types in, removes whitespace
    public static String askForString(String message) {

        String response = Console.input(message);
        String parsedResponse = response.replaceAll("\\W+|\\d+", "").strip().trim();

        if (parsedResponse.length() > 0) {
            return parsedResponse;
        }

        invalid(response);
        return askForString(message);

    }

    public static int askForInt(String message) {

        String response = Console.input(message);
        String parsedResponse = response.replaceAll("\\D+", "");

        if (Parser.compare(response, "^\\d+$")) {
            try {
                return Integer.parseInt(response);
            } catch (NumberFormatException e) {
                invalid(response + "(Too large!!)");

                return askForInt(message);
            }
        }

        invalid(response);
        return askForInt(message);

    }

    public static int bet(int playerChips) {

        int bet = askForInt("Bet: ");

        if (bet <= playerChips) {
            return bet;
        }

        Console.log("Bet -> ( " + bet + " ) exceeds players amount -> ( " + playerChips + " )!");

        return bet(playerChips);
    }


    public static void displayPlayer(Player user) {
        Console.log("Player: " + user.name + "Winnings: " + user.winnings());
    }

    public static void winner(String winnerName, String loserName, int winnerSum, int loserSum) {
        if (21 < loserSum) {
            Console.log(winnerName + " beat " + loserName + ", " + loserName + " busted");
        } else {
            Console.log(winnerName + " beat " + loserName + " with " + winnerSum + " against " + loserSum);
        }
    }

    public static void tie(String name1, String name2, int sum1, int sum2) {
        if (21 < sum1 && 21 < sum2) {
            Console.log(name1 + " tied -> " + name2 + ", both have busted");
        } else if (sum1 == sum2){
            Console.log(name1 + " tied -> " + name1 + " with " + sum1 + " against " + sum2);
        }
    }

    public static void welcome() {
        Console.log("****************( Welcome to blackjack! )****************");
    }

    public static void blackJack() {
        Console.log("BLACKJACK!!!");
    }

    public static void newRound() {
        Console.log("New round starting, new players may join!");
    }

    public static void displayPlayers(ArrayList<Player> players) {
        for (Player user : players) {
            displayPlayer(user);
        }
    }

}