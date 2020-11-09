package blackjack.utils;

import java.util.HashSet;
import java.util.Set;

public class IdGenerator extends Generator {
    public static Set<String> idList = new HashSet<>();

    public static String id() {
        int inital = idList.size() * 1;
        String regex = "[0-9][a-z][A-Z]{9}";
        String id = createGenerator(regex).generate();
        assert id.matches(regex);

        idList.add(id);

        if(idList.size() == inital) {
            return id();
        }

        return id;
    }

}
