package model.data_preparation;

import java.util.ArrayList;
import java.util.HashMap;

import model.map.Map;
import model.territory.Territory;

public class MapOrganizer {

    public MapOrganizer(Map map) {
        organize(map);
    }

    public Map organize(Map map) {


ArrayList<String> level_1_ids = new ArrayList<>();
        ArrayList<String> level_2_ids = new ArrayList<>();
        ArrayList<String> level_3_ids = new ArrayList<>();
        ArrayList<String> level_4_ids = new ArrayList<>();
        ArrayList<String> level_5_ids = new ArrayList<>();
        for (HashMap.Entry<String, Territory> entry : map.entrySet()) {
            if (entry.getKey().length() > 3) {
                ids.add(entry.getKey());
            }
        }

        System.out.println(map.get(ids.get(0)).setSubunits(null););

        return map;
    }
}
