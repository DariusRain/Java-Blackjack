package blackjack.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {

    private String regex = "";
    private Pattern pattern;
    private Matcher matcher;

    public boolean compare(String toParse, String regex) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(toParse);
        return matcher.find();
    }

    public boolean isAce(String toParse) { return compare(toParse, "^Ace" ); }

    public boolean isFace(String toParse) { return compare(toParse, "^Jack|Queen|King"); }

    public boolean isNumber(String toParse) { return compare(toParse.split(" ")[0], "^[2-9]|10$"); }



}
