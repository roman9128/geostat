package model.data_preparation;

import java.util.HashMap;

import model.map.Map;
import model.territory.Territory;

public class MapOrganizer {

    public MapOrganizer() {
    }

    public Map organize(Map map) {
        setTerritoryLevel(map);
        setSubunits(map);
        return map;
    }

    private void setTerritoryLevel(Map map) {
        for (HashMap.Entry<String, Territory> entry : map.getMap().entrySet()) {
            if (entry.getKey().length() < 4) {
                entry.getValue().setLevel(1);
            } else {
                String[] level = entry.getKey().split("_");
                entry.getValue().setLevel(level.length);
            }
        }
    }

    private void setSubunits(Map map) {
        for (HashMap.Entry<String, Territory> entry : map.getMap().entrySet()) {
            if (entry.getKey().length() > 3) {
                String[] ids = entry.getKey().split("_");
                for (int i = 1; i < ids.length; i++) {
                    ids[i] = ids[i - 1] + "_" + ids[i];
                }
                for (int i = 0; i < ids.length - 1; i++) {
                    map.getTerritoryOnID(ids[i]).setSubunit(entry.getKey());
                }
            }
        }
    }
}
