package model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.data_preparation.AdditionalInfoLoader;
import model.data_preparation.BasicInfoLoader;
import model.map.Map;
import model.map.TerritorySorter;
import model.territory.Territory;
import model.territory.TerritoryType;
import model.territory_set.TerritorySet;
import model.types.Operator;

public class Service {
    private Map map;

    public Service() {
        map = getPreparedMap("bd.csv");
        map = addAdditionalInfo("nd.csv");
    }

    public void createTerritorySet(String setName) {
        map.addSet(setName, new TerritorySet());
    }

    public void addTerritoryToSet(String setName, String id) {
        map.getSet().get(setName).addToSet(map, id);
    }

    public void removeTerritoryFromSet(String setName, String id) {
        map.getSet().get(setName).removeFromSet(id);
    }

    public ArrayList<String> getTerritorySetsNames() {
        ArrayList<String> result = new ArrayList<>();
        for (HashMap.Entry<String, TerritorySet> entry : map.getSet().entrySet()) {
            result.add(entry.getKey());
        }
        return result;
    }

    public HashMap<String, TerritorySet> getAllSets() {
        return map.getSet();
    }

    public TerritorySet getSetByName(String setName) {
        return map.getSet().get(setName);
    }

    public void renameSet(String previousSetName, String newSetName) {
        map.renameSet(previousSetName, newSetName);
    }

    public void removeSet(String setName) {
        map.removeSet(setName);
    }

    public String[] getNumericalDataNames() {
        return map.getUserDataNames();
    }

    public String findByName(String name) {
        List<Territory> result = map.findByName(name);
        return printResult(result);
    }

    public String findByTerritoryType(TerritoryType type) {
        List<Territory> result = map.findByTerritoryType(type);
        return printResult(result);
    }

    public String findByLevel(Operator operator, int number) {
        List<Territory> result = map.findByLevel(operator, number);
        return printResult(result);
    }

    public String findByParameter(String dataName, Operator operator, long number) {
        List<Territory> result = map.findByParameter(dataName, operator, number);
        return printResult(result);
    }

    public String findByParameterWithinInterval(String dataName, long number1, long number2) {
        List<Territory> result = map.findByParameterWithinInterval(dataName, number1, number2);
        return printResult(result);
    }

    public Territory chooseTerritory(String id) {
        return map.getTerritoryOnID(id);
    }

    public String getSortedList() {
        StringBuilder builder = new StringBuilder();
        TerritorySorter sorter = new TerritorySorter();
        builder.append("Common list: \n");
        List<Territory> listToShow = sorter.sortTerritories(map.getMapAsHashMap());
        for (Territory territory : listToShow) {
            builder.append("\n");
            builder.append(territory.getName());
            builder.append("\n");
        }
        return builder.toString();
    }

    // public String getList() {
    // StringBuilder builder = new StringBuilder();
    // TerritorySorter sorter = new TerritorySorter();
    // builder.append("Common list: \n");
    // List<Territory> listToShow = sorter.sortTerritories(map.getMapAsHashMap());
    // for (Territory territory : listToShow) {
    // builder.append("\n");
    // builder.append(territory.getName());
    // builder.append("\n");
    // // builder.append(getSortedListOfSubunits(map.getTerritoryID(territory)));
    // List<Territory> subUnitsToShow =
    // sorter.sortTerritories(territory.getSubunits());
    // for (Territory subunit : subUnitsToShow) {
    // builder.append("\n\t");
    // builder.append(subunit.getName());
    // builder.append("\n\t");
    // builder.append(getSortedListOfSubunits(map.getTerritoryID(subunit)));
    // }
    // }
    // return builder.toString();
    // }

    public String getListOfSubunitsOnID(String id) {
        return getListOfSubunits(id, 1);
    }

    private String getListOfSubunits(String id, int depth) {
        if (map.getTerritoryOnID(id).getSubunits() == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        TerritorySorter sorter = new TerritorySorter();
        String tab = "\t";
        builder.append(map.getTerritoryOnID(id).getName());
        builder.append("\n");
        List<Territory> listToShow = sorter.sortTerritories(map.getTerritoryOnID(id).getSubunits());
        for (Territory territory : listToShow) {
            builder.append(tab.repeat(depth));
            builder.append(territory.getName());
            builder.append("\n");
            List<Territory> subUnitsToShow = sorter.sortTerritories(territory.getSubunits());
            for (Territory subunit : subUnitsToShow) {
                builder.append(tab.repeat(depth + 1));
                builder.append(subunit.getName());
                builder.append("\n");
                builder.append(getListOfSubunits(map.getTerritoryID(subunit), depth + 1));
            }
        }
        return builder.toString();
    }

    private String printResult(List<Territory> result) {
        StringBuilder builder = new StringBuilder();
        builder.append("Result: \n");
        if (result.size() == 0) {
            builder.append("Not found");
        }
        for (Territory territory : result) {
            builder.append(territory);
            builder.append("\n");
        }
        return builder.toString();
    }

    private Map getPreparedMap(String path) {
        return new BasicInfoLoader(new File(path)).sendMap();
    }

    private Map addAdditionalInfo(String path) {
        return new AdditionalInfoLoader(map, new File(path)).sendMap();
    }
}