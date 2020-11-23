package blackjack.utils.generators;
import java.security.SecureRandom;
public class Generator {
    static SecureRandom rand = new SecureRandom();
    public static int generate(int max) { return rand.nextInt(max) + 1; }
}
