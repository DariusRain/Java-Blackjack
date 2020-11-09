package blackjack;

import blackjack.utils.Generator;

public class Game {
    public void start() {
        Generator generator = new Generator();
        generator.suit();
        generator.face();
        int count = 1;
        while (count++ <= 52) {
            System.out.println(generator.face()+ " of " + generator.suit());
        }
    }
}
