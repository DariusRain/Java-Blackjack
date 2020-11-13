package blackjack.utils.UserInteractions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static String regex = "";
    private static Pattern pattern;
    private static Matcher matcher;

    public static boolean compare(String toParse, String regex) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(toParse);
        return matcher.find();
    }

    public static boolean isAce(String toParse) { return compare(toParse, "^Ace" ); }

    public static boolean isFace(String toParse) { return compare(toParse, "^Jack|Queen|King"); }

    public static boolean isNumber(String toParse) { return compare(toParse.split(" ")[0], "^[2-9]|10$"); }

    public static int handleAce() { return Menu.choice("Count Ace as 11?") ? 11 : 1; }

}
