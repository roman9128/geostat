package model.data_preparation;

import java.io.File;

import model.map.Map;
import model.territory.Territory;
import model.territory.TerritoryType;

public class BasicInfoLoader extends DataLoader {

    private Map loadedMap;
    private MapOrganizer organizer;

    public BasicInfoLoader(File file) {
        loadedMap = new Map();
        loadData(file);
        organizer = new MapOrganizer();
        organize(loadedMap);
    }

    public Map sendMap() {
        return loadedMap;
    }

    private void organize(Map loadedMap){
        organizer.organize(loadedMap);
    }

    @Override
    void addInfoAboutTerritory(String[] data) {
        addTerritory(sendID(data), sendName(data), sendTerritoryType(data), sendCapital(data));
    }

    private void addTerritory(String id, String name, TerritoryType type, boolean isCapital) {
        Territory territory = new Territory(id, name, type, isCapital);
        loadedMap.addToMap(id, territory);
    }

    private String sendID(String[] data) {
        return data[0];
    }

    private String sendName(String[] data) {
        return data[1];
    }

    private TerritoryType sendTerritoryType(String[] data) {
        return TerritoryType.valueOf(data[2]);
    }

    private boolean sendCapital(String[] data) {
        if (data[3].equals("1")) {
            return true;
        }
        return false;
    }
}