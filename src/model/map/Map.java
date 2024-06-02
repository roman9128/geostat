package model.map;
import java.util.HashMap;

import model.territory.Territory;

public class Map {
    private HashMap<String, Territory> map;
    // private UserData UserData1;
    // private UserData UserData2;
    // private UserData UserData3;
    // private UserData UserData4;
    // private UserData UserData5;

    public Map() {
        map = new HashMap<String, Territory>();
    }

    public void addToMap(String id, Territory territory) {
        map.put(id, territory);
    }

    public HashMap<String, Territory> getMap() {
        return map;
    }
}
