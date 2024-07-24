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
        localizedNames = new Localizator("rus").getLocalization();
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
        addTerritory(sendID(data), sendName(data), sendTerritoryType(data), sendCapital(data));
    }

    private void addTerritory(String id, String name, TerritoryType type, String[] capital) {
        loadedMap.addToMap(id, new Territory(name, type, capital));
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

    private String[] sendCapital(String[] data) {
        String[] capital = new String[2];
        if (!data[2].equals("0")) {
            capital[0] = data[2];
            capital[1] = localizedNames.get(data[2]);
        }
        return capital;
    }
}