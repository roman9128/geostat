package model.data_preparation;

import java.io.File;

import model.map.Map;

public class AdditionalInfoLoader extends DataLoader {
    Map map;

    public AdditionalInfoLoader(Map map, File file) {
        this.map = map;
        loadData(file);
    }

    public Map sendMap() {
        return map;
    }

    @Override
    void addInfoAboutTerritory(String[] data) {
        map.getMapAsHashMap().get(data[0]).setSquare(sendArea(data));
        map.getMapAsHashMap().get(data[0]).setPopulation(sendPopulation(data));
    }

    private long sendArea(String[] data) {
        return Long.parseLong(data[1]);
    }

    private long sendPopulation(String[] data) {
        return Long.parseLong(data[2]);
    }
}