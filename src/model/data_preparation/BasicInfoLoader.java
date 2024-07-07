package model.data_preparation;

import java.io.File;
import java.util.HashMap;

import model.map.Map;
import model.territory.Territory;
import model.territory.TerritoryType;

public class BasicInfoLoader extends DataLoader {

    private Map loadedMap;
    private MapOrganizer organizer;
    private HashMap<String, String> localizedNames;

    public BasicInfoLoader(File file) {
        loadedMap = new Map();
        organizer = new MapOrganizer();
        localizedNames = new Localizator().getLocalization();
        loadData(file);
        organize(loadedMap);
    }

    public Map sendMap() {
        return loadedMap;
    }

    private void organize(Map loadedMap) {
        organizer.organize(loadedMap);
    }

    @Override
    void addInfoAboutTerritory(String[] data) {
        addTerritory(sendID(data), sendName(data), sendTerritoryType(data), sendCapital(data), sendArea(data),
                sendPopulation(data));
    }

    private void addTerritory(String id, String name, TerritoryType type, boolean isCapital, long square,
            long population) {
        Territory territory = new Territory(id, name, type, isCapital, square, population);
        loadedMap.addToMap(id, territory);
    }

    private String sendID(String[] data) {
        return data[0];
    }

    private String sendName(String[] data) {
        return localizedNames.get(data[0]);
    }

    private TerritoryType sendTerritoryType(String[] data) {
        return TerritoryType.valueOf(data[1]);
    }

    private boolean sendCapital(String[] data) {
        if (data[2].equals("1")) {
            return true;
        }
        return false;
    }

    private long sendArea(String[] data) {
        return Long.parseLong(data[3]);
    }

    private long sendPopulation(String[] data) {
        return Long.parseLong(data[4]);
    }
}