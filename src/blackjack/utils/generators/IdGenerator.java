package blackjack.utils.generators;

import java.util.HashMap;

public class IdGenerator extends GeneratorY {
    public static HashMap<String, String> idList = new HashMap<>();

    public static String id(String playerName) {
        int inital = idList.size() * 1;
        String regex = "([0-9]|[a-z]|[A-Z]){4}";
        String id = createGenerator(regex).generate();
        assert id.matches(regex);

        idList.put(id, playerName);

        if(idList.size() == inital) {
            return id(playerName);
        }

        return id;
    }

    public static String toKey(String name, String id) {return name + "-" + id;}

}
