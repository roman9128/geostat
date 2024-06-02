package model;
import java.util.HashMap;

import model.map.Map;
import model.territory.Territory;
import model.territory.TerritoryType;

public class Service {
    private Map map;


    public Service() {
        map = new Map();
    }

    public void addTerritory(String id, int level, String name, TerritoryType type, boolean isCapital) {
        Territory territory = new Territory(id, level, name, type, isCapital);
        map.addToMap(id, territory);
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Список: \n");
        for (HashMap.Entry<String, Territory> territory : map.getMap().entrySet()) {
            builder.append(territory.getKey());
            builder.append(territory.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }
}
