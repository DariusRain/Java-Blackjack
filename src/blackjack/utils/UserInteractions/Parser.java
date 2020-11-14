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

    public static int handleAce() { return Menu.choice("Count Ace as 11?") ? 11 : 1; }

    public static int handleFace() {return FACE_CARD_VALUE;}

    public static int cardValue(String card) {

        if (isAce(card)) {
            return handleAce();
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
