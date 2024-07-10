package model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.territory.Territory;
import model.territory.TerritoryType;
import model.territory.comparators.ComparatorByName;
import model.types.DataType;
import model.types.Operator;

public class Map {
    private HashMap<String, Territory> map;

    public Map() {
        map = new HashMap<String, Territory>();
    }

    public void addToMap(String id, Territory territory) {
        map.put(id, territory);
    }

    public HashMap<String, Territory> getMapAsHashMap() {
        return map;
    }

    public void setMap(HashMap<String, Territory> map) {
        this.map.putAll(map);
    }

    public Territory getTerritoryOnID(String id) {
        return map.get(id);
    }

    public List<Territory> findByName(String name) {
        List<Territory> result = new ArrayList<>();
        for (HashMap.Entry<String, Territory> entry : map.entrySet()) {
            if (entry.getValue().getName().contains(name)) {
                result.add(entry.getValue());
            }
        }
        result.sort(new ComparatorByName());
        return result;
    }

    public List<Territory> findByTerritoryType(TerritoryType type) {
        List<Territory> result = new ArrayList<>();
        for (HashMap.Entry<String, Territory> entry : map.entrySet()) {
            if (entry.getValue().getType().equals(type)) {
                result.add(entry.getValue());
            }
        }
        result.sort(new ComparatorByName());
        return result;
    }

    public List<Territory> findByParameter(DataType type, Operator operator, long number) {
        List<Territory> result = new ArrayList<>();
        for (HashMap.Entry<String, Territory> entry : map.entrySet()) {
            if (operator.equals(Operator.equal)) {
                if (entry.getValue().getNumericalInfoByDataType(type) == number) {
                    result.add(entry.getValue());
                }
            }
            if (operator.equals(Operator.less)) {
                if (entry.getValue().getNumericalInfoByDataType(type) < number) {
                    result.add(entry.getValue());
                }
            }
            if (operator.equals(Operator.more)) {
                if (entry.getValue().getNumericalInfoByDataType(type) > number) {
                    result.add(entry.getValue());
                }
            }
        }
        result.sort(new ComparatorByName());
        return result;
    }
}
