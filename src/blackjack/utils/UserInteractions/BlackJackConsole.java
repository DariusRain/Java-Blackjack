package blackjack.utils.UserInteractions;

import blackjack.players.Dealer;
import blackjack.players.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackJackConsole extends Console {

    public static void displayPlayer(String name, String id, int chips, int winnings, int normalCardSum, int splitCardSum, boolean didSplit, HashMap<String, ArrayList<String>> cards) {
        clearScreen();
        log("( " + name  + "-" + id + " ) " +"Chips: " + chips + " Winnings: " + winnings);

        Console.logf("Hand: ");
        for(String card: cards.get("normal")) {
            Console.logf(card + " ");
        }

        Console.log("\nTotal: " + normalCardSum);

        if (didSplit) {
            Console.logf("Split hand:");
            for (String card: cards.get("split")) {
                Console.logf(card + " ");
            }
            Console.log("Split Total: " + splitCardSum);
        }
    }

}
