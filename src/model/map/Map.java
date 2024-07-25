package model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.territory.Territory;
import model.territory.TerritoryType;
import model.territory.comparators.ComparatorByName;
import model.territory_set.TerritorySet;
import model.types.Operator;

public class Map {
    private HashMap<String, Territory> map;
    private HashMap<String, TerritorySet> sets;
    private String[] userDataNames;

    public Map() {
        map = new HashMap<String, Territory>();
    }

    public HashMap<String, TerritorySet> getSet() {
        return sets;
    }

    public void addSet(String setName, TerritorySet terrSet) {
        if (sets == null) {
            sets = new HashMap<String, TerritorySet>();
        }
        this.sets.put(setName, terrSet);
    }

    public void removeSet(String setName){
        sets.remove(setName);
    }

    public void renameSet(String previousSetName, String newSetName) {
        TerritorySet newSet = new TerritorySet();
        newSet.setTerritories(getSet().get(previousSetName).getTerritories());
        newSet.setNumericalData(getSet().get(previousSetName).getNumericalData());
        addSet(newSetName, newSet);
        removeSet(previousSetName);
    }

    public String[] getUserDataNames() {
        return userDataNames;
    }

    public void setUserDataNames(String[] userDataNames) {
        this.userDataNames = userDataNames;
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

    public List<Territory> findByLevel(Operator operator, int number) {
        List<Territory> result = new ArrayList<>();
        for (HashMap.Entry<String, Territory> entry : map.entrySet()) {
            if (operator.equals(Operator.equal)) {
                if (entry.getValue().getLevel() == number) {
                    result.add(entry.getValue());
                }
            }
            if (operator.equals(Operator.less)) {
                if (entry.getValue().getLevel() < number) {
                    result.add(entry.getValue());
                }
            }
            if (operator.equals(Operator.more)) {
                if (entry.getValue().getLevel() > number) {
                    result.add(entry.getValue());
                }
            }
        }
        result.sort(new ComparatorByName());
        return result;
    }

    public List<Territory> findByParameter(String dataName, Operator operator, long number) {
        List<Territory> result = new ArrayList<>();
        try {
            for (HashMap.Entry<String, Territory> entry : map.entrySet()) {
                if (operator.equals(Operator.equal)) {
                    if (entry.getValue().getNumericalData().get(dataName) == number) {
                        result.add(entry.getValue());
                    }
                }
                if (operator.equals(Operator.less)) {
                    if (entry.getValue().getNumericalData().get(dataName) < number) {
                        result.add(entry.getValue());
                    }
                }
                if (operator.equals(Operator.more)) {
                    if (entry.getValue().getNumericalData().get(dataName) > number) {
                        result.add(entry.getValue());
                    }
                }
            }
            result.sort(new ComparatorByName());
        } catch (Exception e) {
            System.err.println("ERROR: There's no such data name");
        }
        return result;
    }

    public List<Territory> findByParameterWithinInterval(String dataName, long number1, long number2) {
        List<Territory> result = new ArrayList<>();
        try {
            long smallerNumber;
            long largerNumber;
            if (number1 > number2) {
                largerNumber = number1;
                smallerNumber = number2;
            } else {
                smallerNumber = number1;
                largerNumber = number2;
            }
            for (HashMap.Entry<String, Territory> entry : map.entrySet()) {
                if (smallerNumber <= entry.getValue().getNumericalData().get(dataName)
                        && entry.getValue().getNumericalData().get(dataName) <= largerNumber) {
                    result.add(entry.getValue());
                }
            }
            result.sort(new ComparatorByName());
        } catch (Exception e) {
            System.err.println("ERROR: There's no such data name");
        }
        return result;
    }
}