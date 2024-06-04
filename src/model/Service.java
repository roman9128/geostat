package model;

import java.io.File;
import java.util.HashMap;

import model.data_preparation.DataLoader;
import model.map.Map;
import model.territory.Territory;

public class Service {
    private Map map;

    public Service() {
        map = new Map();
        fillTheMap(new File("st.csv"));
        fillTheMap(new File("sts.csv"));
    }

    private void fillTheMap(File file) {
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
            builder.append(territory.getKey());
            builder.append(": ");
            builder.append(territory.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }
}
