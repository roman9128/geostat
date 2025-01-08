package rt.model.territory_set;

import java.util.HashMap;

import rt.model.territory.Territory;

public class TerritorySet {

    private HashMap<String, Territory> territoriesInSet;
    private HashMap<String, Long> numericalData;

    public TerritorySet() {
    }

    
    public void addToSet(String id, Territory territory) {
        if (territoriesInSet == null) {
            territoriesInSet = new HashMap<>();
        }
        this.territoriesInSet.put(id, territory);
        calculateNumericalDataForSet(territoriesInSet);
    }

    public void removeFromSet (String id){
        this.territoriesInSet.remove(id);
        calculateNumericalDataForSet(territoriesInSet);
    }
    
    private void calculateNumericalDataForSet(HashMap<String, Territory> territories) {
        HashMap<String, Long> dataToAdd = new HashMap<>();
        if (numericalData == null) {
            numericalData = new HashMap<>();
        }
        for (HashMap.Entry<String, Territory> territoryEntry : territories.entrySet()) {
            for (HashMap.Entry<String, Long> entry : territoryEntry.getValue().getNumericData().entrySet()) {
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
        return territoriesInSet;
    }
    
    public HashMap<String, Long> getNumericalData() {
        return numericalData;
    }
    
    public void setTerritories(HashMap<String, Territory> territoriesInSet) {
        this.territoriesInSet = territoriesInSet;
    }

    public void setNumericalData(HashMap<String, Long> numericalData) {
        this.numericalData = numericalData;
    }
    
    @Override
    public String toString() {
        return "includes\nterritories:\n" + territoriesInSet + "\nnumerical data of set:\n" + numericalData;
    }
}