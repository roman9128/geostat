package rt.model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import rt.model.enums.Operator;
import rt.model.enums.TerritoryType;
import rt.model.territory.Territory;
import rt.model.territory.comparators.ComparatorByName;
import rt.model.territory_set.TerritorySet;

public class Map {

    private HashMap<String, Territory> map;
    private HashMap<String, TerritorySet> sets;
    private String[] userNumericDataNames;

    public Map() {
        map = new HashMap<>();
    }

    public HashMap<String, TerritorySet> getSetsAsHashMap() {
        return sets;
    }

    public void addSet(String setName, TerritorySet terrSet) {
        if (sets == null) {
            sets = new HashMap<>();
        }
        this.sets.put(setName, terrSet);
    }

    public void removeSet(String setName) {
        sets.remove(setName);
    }

    public void renameSet(String previousSetName, String newSetName) {
        TerritorySet newSet = new TerritorySet(newSetName);
        newSet.setTerritories(getSetsAsHashMap().get(previousSetName).getTerritories());
        newSet.setNumericData(getSetsAsHashMap().get(previousSetName).getNumericData());
        addSet(newSetName, newSet);
        removeSet(previousSetName);
    }

    public String[] getUserNumericDataNames() {
        return userNumericDataNames;
    }

    public void setUserNumericDataNames(String[] userNumericDataNames) {
        this.userNumericDataNames = userNumericDataNames;
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

    public String getTerritoryID(Territory territory) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().equals(territory))
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
    }

    public List<Territory> findByName(String name) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().getName().contains(name))
                .map(entry -> entry.getValue())
                .toList();
    }

    public List<Territory> findByTerritoryType(TerritoryType type) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().getType().equals(type))
                .map(entry -> entry.getValue())
                .toList();
    }

    public List<Territory> findByLevel(Operator operator, int number) {
        switch (operator) {
            case EQUAL -> {
                return map.entrySet().stream()
                        .filter(entry -> entry.getValue().getLevel() == number)
                        .map(entry -> entry.getValue())
                        .sorted(new ComparatorByName())
                        .toList();
            }
            case LESS -> {
                return map.entrySet().stream()
                        .filter(entry -> entry.getValue().getLevel() < number)
                        .map(entry -> entry.getValue())
                        .sorted(new ComparatorByName())
                        .toList();
            }
            case MORE -> {
                return map.entrySet().stream()
                        .filter(entry -> entry.getValue().getLevel() > number)
                        .map(entry -> entry.getValue())
                        .sorted(new ComparatorByName())
                        .toList();
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }

    public List<Territory> findByParameter(String dataName, Operator operator, long number) {
        if (Arrays.asList(userNumericDataNames).contains(dataName)) {
            switch (operator) {
                case MORE -> {
                    return map.entrySet().stream()
                            .filter(entry -> entry.getValue().getNumericData().get(dataName) > number)
                            .map(entry -> entry.getValue())
                            .sorted(new ComparatorByName())
                            .toList();
                }
                case LESS -> {
                    return map.entrySet().stream()
                            .filter(entry -> entry.getValue().getNumericData().get(dataName) < number)
                            .map(entry -> entry.getValue())
                            .sorted(new ComparatorByName())
                            .toList();
                }
                case EQUAL -> {
                    return map.entrySet().stream()
                            .filter(entry -> entry.getValue().getNumericData().get(dataName) == number)
                            .map(entry -> entry.getValue())
                            .sorted(new ComparatorByName())
                            .toList();
                }
                default -> {
                    return new ArrayList<>();
                }
            }
        } else {
            return new ArrayList<>();
        }
    }

    public List<Territory> findByParameterWithinInterval(String dataName, long number1, long number2) {
        if (Arrays.asList(userNumericDataNames).contains(dataName)) {
            long smallerNumber;
            long largerNumber;
            if (number1 > number2) {
                largerNumber = number1;
                smallerNumber = number2;
            } else {
                smallerNumber = number1;
                largerNumber = number2;
            }
            return map.entrySet().stream()
                    .filter(entry
                            -> smallerNumber <= entry.getValue().getNumericData().get(dataName) && entry.getValue().getNumericData().get(dataName) <= largerNumber)
                    .map(entry -> entry.getValue())
                    .sorted(new ComparatorByName())
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }
}
