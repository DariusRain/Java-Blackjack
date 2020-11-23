package blackjack.utils.generators;

import java.util.HashMap;

public class IdGenerator extends Generator {
    public static HashMap<String, String> idList = new HashMap<>();

    public static String id(String playerName) {
        int inital = idList.size() * 1;
        String id = generate(1000) + "";
        idList.put(id, playerName);

        if(idList.size() == inital) {
            return id(playerName);
        }

        return id;
    }

    public static String toKey(String name, String id) {return name + "-" + id;}

}
