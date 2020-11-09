package blackjack.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {

    private String regex = "";
    private Pattern pattern;
    private Matcher matcher;

    public boolean isAce(String toParse) { return compare("Ace", toParse); }

    public boolean isFaceCard(String toParse) { return compare("Jack|Queen|King", toParse); }

    public boolean isNumber(String toParse) { return compare("[2-9]|10", toParse.split(" ")[0]); }


    public boolean compare(String toParse, String regex) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(toParse);
        return matcher.find();
    }

}
