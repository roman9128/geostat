package model.map;

import java.util.HashMap;

import model.territory.Territory;

public class Map {
    private HashMap<String, Territory> map;

    public Map() {
        map = new HashMap<String, Territory>();
    }

    public void addToMap(String id, Territory territory) {
        map.put(id, territory);
    }

    public HashMap<String, Territory> getMapAsHashMap() {
        return map;
    }

    public void setMap(HashMap<String, Territory> map) {
        this.map.putAll(map);
    }

    public Territory getTerritoryOnID(String id) {
        return map.get(id);
    }
}
