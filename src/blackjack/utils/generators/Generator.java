package blackjack.utils.generators;

 // https://stackoverflow.com/questions/22115/using-regex-to-generate-strings-rather-than-match-them
import nl.flotsam.xeger.Xeger; // https://code.google.com/archive/p/xeger/

import javax.security.auth.callback.NameCallback;

public class Generator {
    /**
          * Instantiates a new Xeger class with passed in regex pattern as a argument.
          * @param regex -> To be passed as argument in Xeger's constructor.
          * @return Xeger -> Will be used in other methods for its "generate()" method.
     * */
    public static Xeger createGenerator(String regex) {
        return new Xeger(regex);
    }

    /**
          * Generates a random string matching any arbitrary regex string matching the argument using Google's Xeger library.
          * @param regex -> Could be any regex pattern (i.e [0-9], [A-Z], anyword, thisword|thatword|orthisword, )
          * @return String -> Any string that matches
     */
    public static String generate(String regex) {
        String response = createGenerator(regex).generate();
        assert response.matches(regex);
        return response;
    }


//
}
