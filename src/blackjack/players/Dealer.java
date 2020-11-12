package blackjack.players;

import blackjack.materials.Deck;
import blackjack.materials.Menu;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Dealer extends Player {

    private Deck deck = new Deck();
    private int cardCount = 0;

    public Dealer(String name) {
        super(name);
        deck.shuffle();
        deck.setDeck();
    }

//    @Override
//    public void hit(String card) {
//        checkCardCount();
//        this.hit(card);
//    }

    // Checks if dealer has hit limit
    public boolean limit() { return 17 < cardSum; }

    // Checks if deck is on last card then reshuffles the deck.
    public void checkCardCount() {
        if(deck.getCards().size() <= cardCount) {
            cardCount = 0;
            deck.shuffle();
            deck.setDeck();
        }
    }

    // loops through O(size * 2) and deals the initial deal before game starts.
    public void deal(LinkedHashMap<String, Class<Player>> players) {
        // https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap
        int dealCount = 1;
        while (dealCount++ < 2 ) {

            // Class made for iterating collections,  similar to Scanner.nextLine()
            Iterator iterator = players.entrySet().iterator();

            while (iterator.hasNext()) {
                checkCardCount();

                // This returns the key and value in object.
                Map.Entry obj = (Map.Entry)iterator.next();
                Player onPlayer = (Player) obj.getValue();

                // Only classes inside the player package can utilize protected field values.
                onPlayer.playerHand.addCard(deck.draw(cardCount++), false);
            }

            // I know this function call is repetitive, will look into an alternative afterwards.
            checkCardCount();

            // This is the the Dealer's hand
            playerHand.addCard(deck.draw(cardCount++), false);

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

                while(Menu.choice("Hit")) {
                    onPlayer.hit(deck.draw(cardCount++));
                    onPlayer.didBust();
                }

            }
            // Else must be on dealer
            else {
                while (!limit()) {
                    hit(deck.draw(cardCount++));
                    didBust();
                }
                break;
            }

        }

    }

}
