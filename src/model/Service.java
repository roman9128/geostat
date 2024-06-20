package model;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import model.data_preparation.AdditionalInfoLoader;
import model.data_preparation.BasicInfoLoader;
import model.map.Map;
import model.map.TerritorySorter;
import model.territory.Territory;

public class Service {
    private Map map;

    public Service() {
        map = getPreparedMap("fd.csv");
        // addAdditionalInfo("ain.csv");
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Common list: \n");
        for (HashMap.Entry<String, Territory> territory : map.getMapAsHashMap().entrySet()) {
            builder.append(territory.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }

    public String getSortedList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Common list: \n");
        TerritorySorter sorter = new TerritorySorter(map);
        List<Territory> listToShow = sorter.getTerritories();
        for (Territory territory : listToShow) {
            builder.append("\n");
            builder.append(territory);
            builder.append("\n");
        }
        return builder.toString();
    }

    private Map getPreparedMap(String path) {
        return new BasicInfoLoader(new File(path)).sendMap();
    }

    private Map addAdditionalInfo(String path) {
        return new AdditionalInfoLoader(map, new File(path)).sendMap();
    }
}