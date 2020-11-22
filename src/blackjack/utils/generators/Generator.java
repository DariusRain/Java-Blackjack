package blackjack.utils.generators;
import blackjack.utils.UserInteractions.Console;

import java.security.SecureRandom;
public class Generator {
    static SecureRandom rand = new SecureRandom();
    public static int generate(int max) {
        int c = rand.nextInt(max);
        Console.log(c + "");
        return c;
    }
}
