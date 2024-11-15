package rt.model.territory_set;

import java.util.HashMap;

import rt.model.map.Map;
import rt.model.territory.Territory;

public class TerritorySet {

    private HashMap<String, Territory> territories;
    private HashMap<String, Long> numericalData;

    public TerritorySet() {
    }

    
    public void addToSet(Map map, String id) {
        if (territories == null) {
            territories = new HashMap<>();
        }
        this.territories.put(id, map.getTerritoryOnID(id));
        calculateNumericalDataForSet(territories);
    }

    public void removeFromSet (String id){
        this.territories.remove(id);
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
        numericalData.clear();
        numericalData.putAll(dataToAdd);
    }
    
    public HashMap<String, Territory> getTerritories() {
        return territories;
    }
    
    public HashMap<String, Long> getNumericalData() {
        return numericalData;
    }
    
    public void setTerritories(HashMap<String, Territory> territories) {
        this.territories = territories;
    }

    public void setNumericalData(HashMap<String, Long> numericalData) {
        this.numericalData = numericalData;
    }
    
    @Override
    public String toString() {
        return "includes\nterritories:\n" + territories + "\nnumerical data of set:\n" + numericalData;
    }
}