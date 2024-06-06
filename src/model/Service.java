package model;

import java.io.File;
import java.util.HashMap;

import model.data_preparation.DataLoader;
import model.data_preparation.MapOrganizer;
import model.map.Map;
import model.territory.Territory;

public class Service {
    private Map map;

    public Service() {
        map = new Map();
        fillTheMap("st.csv");
        fillTheMap("sts.csv");
        fillTheMap("fd.csv");
        organize(map);
    }

    private Map organize(Map map) {
        MapOrganizer mapOrganizer = new MapOrganizer(map);
        return mapOrganizer.organize(map);
    }

    private void fillTheMap(String path) {
        File file = new File(path);
        map.setMap(getMapFromFile(file));
    }

    private HashMap<String, Territory> getMapFromFile(File file) {
        DataLoader dataLoader = new DataLoader(file);
        return dataLoader.sendMap();
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Common list: \n");
        for (HashMap.Entry<String, Territory> territory : map.getMap().entrySet()) {
            builder.append(territory.getValue());
            builder.append("\n");
        }
        return builder.toString();
        
    }
}
