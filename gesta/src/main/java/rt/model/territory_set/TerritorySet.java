package rt.model.territory_set;

import java.util.HashMap;

import rt.model.localizator.CommonLocalizator;
import rt.model.printer.HashMapPrinter;
import rt.model.territory.Territory;

public class TerritorySet {

    private String name;
    private HashMap<String, Territory> territoriesInSet;
    private HashMap<String, Long> numericData;

    public TerritorySet(String name) {
        this.name = name;
    }

    public void addToSet(String id, Territory territory) {
        if (territoriesInSet == null) {
            territoriesInSet = new HashMap<>();
        }
        this.territoriesInSet.put(id, territory);
        calculateNumericDataForSet(territoriesInSet);
    }

    public void removeFromSet(String id) {
        this.territoriesInSet.remove(id);
        calculateNumericDataForSet(territoriesInSet);
    }

    private void calculateNumericDataForSet(HashMap<String, Territory> territories) {
        HashMap<String, Long> dataToAdd = new HashMap<>();
        if (numericData == null) {
            numericData = new HashMap<>();
        }
        for (HashMap.Entry<String, Territory> territoryEntry : territories.entrySet()) {
            for (HashMap.Entry<String, Long> entry : territoryEntry.getValue().getNumericData().entrySet()) {
                if (dataToAdd.get(entry.getKey()) == null) {
                    dataToAdd.put(entry.getKey(), 0l);
                }
                dataToAdd.put(entry.getKey(), dataToAdd.get(entry.getKey()) + entry.getValue());
            }
        }
        numericData.clear();
        numericData.putAll(dataToAdd);
    }

    public HashMap<String, Territory> getTerritories() {
        return territoriesInSet;
    }

    public HashMap<String, Long> getNumericData() {
        return numericData;
    }

    public void setTerritories(HashMap<String, Territory> territoriesInSet) {
        this.territoriesInSet = territoriesInSet;
    }

    public void setNumericData(HashMap<String, Long> numericData) {
        this.numericData = numericData;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(CommonLocalizator.getLocalizedText("NAME"))
                .append(": ")
                .append(name);
        builder.append(System.lineSeparator())
                .append(CommonLocalizator.getLocalizedText("INCLUDES"))
                .append(": ");
        if (territoriesInSet == null) {
            builder.append(CommonLocalizator.getLocalizedText("NO_INFO"));
        } else {
            builder.append(HashMapPrinter.printTerritoryNames(territoriesInSet));
        }
        builder.append(System.lineSeparator())
                .append(CommonLocalizator.getLocalizedText("NUMERIC"))
                .append(": ");
        if (numericData == null) {
            builder.append(CommonLocalizator.getLocalizedText("NO_INFO"));
        } else {
            builder.append(HashMapPrinter.printHashMap(numericData));
        }
        return builder.toString();
    }
}
