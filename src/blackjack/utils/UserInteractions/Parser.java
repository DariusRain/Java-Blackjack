package blackjack.utils.UserInteractions;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final int FACE_CARD_VALUE = 10;

    public static boolean compare(String toParse, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(toParse);
        return matcher.find();
    }

    public static boolean isAce(String toParse) { return compare(toParse, "Ace" ); }

    public static boolean isFace(String toParse) { return compare(toParse, "Jack|Queen|King"); }

    public static boolean isNumber(String toParse) { return compare(toParse, "^♡|♣|♠|♢[2-9]|10$"); }

    public static int handleNumber(String toParse) {return Integer.parseInt(toParse.replaceAll("♡|♣|♠|♢", ""));}

    public static int handleAce(int cardSum) {
        if (cardSum + 11 <= 21) {
            return 11;
        } else {
            return 1;
        }
    }

    public static int handleFace() {return FACE_CARD_VALUE;}

    public static boolean isBlackJack(int cardSum) { return cardSum == 21; }

    public static int cardValue(String card, int cardSum) {

        if (isAce(card)) {
            return handleAce(cardSum);
        }

        if (isFace(card)) {
           return handleFace();
        }

        if (isNumber(card)) {
            return handleNumber(card);
        }

        return 0;
    }

}
