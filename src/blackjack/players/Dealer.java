package blackjack.players;

import blackjack.materials.Deck;
import blackjack.utils.UserInteractions.Menu;
import blackjack.utils.UserInteractions.Console;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Player {

    private Deck deck = new Deck();
    private static final int RULE = 17;

    public Dealer(String name) {
        super(name);
    }


    // Checks if dealer has hit limit
    public boolean limit() {
        return RULE < normalCardSum || (didSplit && RULE < splitCardSum);
    }


    public void getBuyIn(Player user) {
        int numberOfChips = Menu.askForInt("Buy in: $");
        user.buyIn(numberOfChips);
    }


    // loops through O(size * 2) and deals the initial deal before game starts.
    public void dealRound(LinkedHashMap<String, Player> players) {
        // https://stackoverflow.com/questions/46898/how-do-i-efficiently-iterate-over-each-entry-in-a-java-map
        int dealCount = 0;
        while ( dealCount++ < 2 ) {
            // Class made for iterating collections,  similar to Scanner.nextLine()
            Iterator iterator = players.entrySet().iterator();
            Console.log("Deal count: " + dealCount);

            while (iterator.hasNext()) {
                // This returns the key and value in object.
                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();

                if (dealCount == 1) {
                    onPlayer.display();
                    int numberOfChips = Menu.bet(onPlayer.chips);
                    onPlayer.bet(numberOfChips);
                }

                onPlayer.addCard(deck.draw(), false);

            }

            // This is the the Dealer's hand
            addCard(deck.draw(), false);


        }
    }

    // loops through O((playersSize + playerHits) + dealerHits) times and asks a player for a hit until player says no.
    public void dealHits(LinkedHashMap<String, Player> players) {

        // https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        Iterator iterator = players.entrySet().iterator();

        while(true) {

            // If there is another player
            if(iterator.hasNext()) {

                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();
                onPlayer.countCards();
                onPlayer.display();
                while(Menu.choice("Hit")) {
                    onPlayer.countCards();
                    onPlayer.hit(deck.draw(), false);
                    onPlayer.display();
                    if(onPlayer.didBust()) {
                        break;
                    }
                }

            }

            // Else must be on dealer
            else {
                this.display();
                while (true) {
                    this.hit(deck.draw(), false);
                    this.display();
                    if(limit()) {
                        this.didBust();
                        break;
                    }
                }
                break;
            }

        }

    }

    public void dispense(LinkedHashMap<String, Player> players) {
            Iterator iterator = players.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();

                // If dealer won
                if ( onPlayer.busted && !this.busted || onPlayer.normalCardSum < this.normalCardSum) {
                    Menu.winner(this.name, onPlayer.name, this.normalCardSum, onPlayer.normalCardSum);
                    onPlayer.lost();
                }
                // if player won
                if (this.busted && !onPlayer.busted || this.normalCardSum < this.normalCardSum ) {
                    Menu.winner(onPlayer.name, this.name, onPlayer.normalCardSum, this.normalCardSum);
                    onPlayer.win();
                }

                // if tie
                if (this.busted && onPlayer.busted || this.normalCardSum == onPlayer.normalCardSum) {
                    Menu.tie(this.name, onPlayer.name, this.normalCardSum, onPlayer.normalCardSum);
                }

                // Clear player's fields for new hand
                onPlayer.clear();

            }
            // Like above but for dealer.
            this.clear();
        }

}
