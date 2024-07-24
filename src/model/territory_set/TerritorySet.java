package model.territory_set;

import java.util.HashMap;

import model.map.Map;
import model.territory.Territory;

public class TerritorySet {

    private HashMap<String, Territory> territories;
    private HashMap<String, Long> numericalData;

    public TerritorySet() {
    }

    public void addToSet(Map map, String ID) {
        if (territories == null) {
            territories = new HashMap<>();
        }
        this.territories.put(ID, map.getTerritoryOnID(ID));
        calculateNumericalDataForSet(territories);
    }

    private void calculateNumericalDataForSet(HashMap<String, Territory> territories) {
        HashMap<String, Long> dataToAdd = new HashMap<>();
        if (numericalData == null) {
            numericalData = new HashMap<>();
        }
        for (HashMap.Entry<String, Territory> territoryEntry : territories.entrySet()) {
            for (HashMap.Entry<String, Long> entry : territoryEntry.getValue().getNumericalData().entrySet()) {
                if (dataToAdd.get(entry.getKey()) == null) {
                    dataToAdd.put(entry.getKey(), 0l);
                }
                dataToAdd.put(entry.getKey(), dataToAdd.get(entry.getKey()) + entry.getValue());
            }
        }
        numericalData.putAll(dataToAdd);
    }

    public HashMap<String, Territory> getTerritories() {
        return territories;
    }

    public HashMap<String, Long> getNumericalData() {
        return numericalData;
    }

    @Override
    public String toString() {
        return "includes\n[territories=" + territories + "\nnumerical data of set = " + numericalData
                + "]";
    }
}