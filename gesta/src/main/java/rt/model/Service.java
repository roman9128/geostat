package rt.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rt.model.data_preparation.DataPreparationService;
import rt.model.enums.Operator;
import rt.model.enums.TerritoryType;
import rt.model.map.Map;
import rt.model.map.TerritorySorter;
import rt.model.territory.Territory;
import rt.model.territory_set.TerritorySet;

public class Service {
    private String language;
    private Map map;

    public Service(String language) {
        this.language = language; // нужно из файла подтягивать язык
        map = new DataPreparationService(language).loadAndPrepareData();
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

    // public String[] getNumericalDataNames() {
    //     return map.getUserDataNames();
    // }

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
        builder.append("Common list: ");
        builder.append(System.lineSeparator());
        List<Territory> listToShow = sorter.sortTerritories(map.getMapAsHashMap());
        for (Territory territory : listToShow) {
            builder.append(System.lineSeparator());
            builder.append(territory.getName());
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public String getListOfSubunitsOnID(String id) {
        Territory territory = map.getTerritoryOnID(id);
        return territory.getName() + System.lineSeparator() + getListOfSubunits(territory, 1);
    }

    private String getListOfSubunits(Territory territory, int depth) {
        if (territory.getSubunits() == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        TerritorySorter sorter = new TerritorySorter();
        String tab = "\t";
        List<Territory> subunits = sorter.sortTerritories(territory.getSubunits());
        for (Territory subunit : subunits) {
            builder.append(tab.repeat(depth));
            builder.append(subunit.getName());
            builder.append(System.lineSeparator());
            builder.append(getListOfSubunits(subunit, depth + 1));
        }
        return builder.toString();
    }

    private String printResult(List<Territory> result) {
        StringBuilder builder = new StringBuilder();
        builder.append("Result: ");
        builder.append(System.lineSeparator());
        if (result.size() == 0) {
            builder.append("Not found");
        }
        for (Territory territory : result) {
            builder.append(territory);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

}