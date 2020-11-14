package com.coderain;
import blackjack.Game;
import blackjack.materials.Deck;
import blackjack.players.Dealer;
import blackjack.utils.UserInteractions.Menu;

/**
 * <h1>Java-Blackjack </h1>
 *
 * <p>
 *
 *  Project:
 *      Implement Blackjack the game where the player plays against a dealer.
 *
 *  Blackjack rules:
 *      1 player (advanced challenge x players);
 *      1 "dealer"
 *      52 card deck (no jokers);
 *
 *  Versions:
 *      v0.1 Instantiate a card with a value and suit
 *      v0.2 Instantiate a deck and build it with 52 unique cards.
 *      v0.3 Draw a card from the deck.
 *      @see blackjack.utils.generators.CardGenerator#nextCard()
 *      @see blackjack.materials.Deck#draw()
 *
 *      v0.4 Confirm if I can't draw the same card twice from a deck. (ie the card appears to be removed).
 *      v0.5 Detect when the deck is empty.
 *      v0.6 Shuffle the order of the deck.
 *      v1.0 Draw two cards from the deck into a hand.
 *      @see java.util.Set
 *      @see blackjack.materials.Deck#draw()
 *
 *
 *      v1.1 Determine the value of the hand based on the values
 *      v1.2 Determine the BlackJack value of the hand.
 *      @see blackjack.players.Hand#countCards()
 *      @see blackjack.players.Hand#hit(java.lang.String, boolean)
 *      @see blackjack.utils.UserInteractions.Parser#cardValue(java.lang.String)
 *
 *      v1.3 Draw another card into the hand and determine if a Bust has occurred.
 *      @see blackjack.players.Hand#didBust()
 *
 *      v1.4 End my turn.
 *      v2.0 Have than one player at the same time.
 *      v2.1 Each player will take their turn.
 *      v2.2 TODO -> System that will automate the player process following the dealer rules.
 *      v2.3 Compare the player hand against the dealer hand and determine if the player has won.
 *      @see Dealer#dealHits(java.util.LinkedHashMap)
 *      @see blackjack.utils.UserInteractions.Menu#choice(java.lang.String)
 *
 *
 *      v2.4 Bet points.
 *      @see blackjack.players.Dealer#dealRound(java.util.LinkedHashMap)
 *
 *      v2.5 The end game process will award points.
 *      @see blackjack.players.Dealer#dispense(java.util.LinkedHashMap)
 *
 *  Additional challenges:
 *  TODO
 *      v3.0 I can detect BlackJack (face card and an ace)
 *      v3.1 if player has BlackJack they gain 2.5x their bet.
 *      v3.2 implement the Double feature.
 *      v4.0 implement the split feature.
 *
 * </p>
 *
 * @author  Darius Rain
 * @version 2.5
 * @since   2020-11-14
 * @github https://www.github.com/DariusRain/Java-Blackjack
 */


public class Main {

    public static void main(String[] args) {
	// write your code here
        Game blackjack = new Game();
    //        game.testDeck();
        blackjack.start();
    }

}
