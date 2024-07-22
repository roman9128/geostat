package model.territory_set;

import java.util.HashMap;

import model.map.Map;
import model.territory.Territory;

public class TerritorySet {

    private String setName;
    private HashMap<String, Territory> territories;
    private HashMap<String, Long> numericalData;

    public TerritorySet(String setName) {
        this.setName = setName;
    }

    public void addToSet(Map map, String ID) {
        if (territories == null) {
            territories = new HashMap<>();
        }
        this.territories.put(ID, map.getTerritoryOnID(ID));
        if (numericalData == null) {
            numericalData = new HashMap<>();
            this.numericalData = map.getTerritoryOnID(ID).getNumericalData();
        } else {
            for (HashMap.Entry<String, Long> entry : map.getTerritoryOnID(ID).getNumericalData().entrySet()) {
                this.numericalData.put(entry.getKey(), entry.getValue() + this.numericalData.get(entry.getKey()));
            }
        }
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public HashMap<String, Territory> getTerritories() {
        return territories;
    }

    public HashMap<String, Long> getNumericalData() {
        return numericalData;
    }
}