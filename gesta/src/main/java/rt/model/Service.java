package rt.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rt.model.data_preparation.DataPreparationService;
import rt.model.enums.Operator;
import rt.model.enums.TerritoryType;
import rt.model.localizator.CommonLocalizator;
import rt.model.localizator.LocalizationLoader;
import rt.model.map.Map;
import rt.model.map.TerritorySorter;
import rt.model.territory.Territory;
import rt.model.territory_set.TerritorySet;

public class Service {

    private final File language = new File("language.txt");
    private final Map map;

    public Service() {
        new LocalizationLoader(language);
        map = new DataPreparationService().loadAndPrepareData();
    }

    public void createTerritorySet(String setName) {
        map.addSet(setName, new TerritorySet());
    }

    public void addTerritoryToSet(String setName, String id) {
        map.getSet().get(setName).addToSet(id, map.getTerritoryOnID(id));
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
        return printResult(map.findByName(name));
    }

    public String findByTerritoryType(TerritoryType type) {
        return printResult(map.findByTerritoryType(type));
    }

    public String findByLevel(Operator operator, int number) {
        return printResult(map.findByLevel(operator, number));
    }

    public String findByParameter(String dataName, Operator operator, long number) {
        return printResult(map.findByParameter(dataName, operator, number));
    }

    public String findByParameterWithinInterval(String dataName, long number1, long number2) {
        return printResult(map.findByParameterWithinInterval(dataName, number1, number2));
    }

    public Territory chooseTerritory(String id) {
        return map.getTerritoryOnID(id);
    }

    public String getListOfTerritoriesWithTheHighestLevelOnMap() {
        StringBuilder builder = new StringBuilder();
        builder.append(getListTitle("LIST"));
        List<Territory> listToShow = new TerritorySorter().sortTerritories(map.getMapAsHashMap());
        for (Territory territory : listToShow) {
            builder.append(System.lineSeparator())
                    .append(territory.getName())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    public String getListOfSubunitsByTheirLevelOnID(String id, int requiredDepth) {
        StringBuilder builder = new StringBuilder();
        Territory territory = map.getTerritoryOnID(id);
        builder.append(getListTitle("LIST"))
                .append(territory.getName())
                .append(System.lineSeparator())
                .append(getListOfSubunits(territory, 1, requiredDepth));
        return builder.toString();
    }

    private String getListOfSubunits(Territory territory, int depth, int requiredDepth) {
        if (territory.getSubunits() == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String tab = "\t";
        List<Territory> subunits = new TerritorySorter().sortTerritories(territory.getSubunits());
        for (Territory subunit : subunits) {
            builder.append(tab.repeat(depth))
                    .append(subunit.getName())
                    .append(System.lineSeparator());
            if (requiredDepth > 0) {
                builder.append(getListOfSubunits(subunit, depth + 1, requiredDepth - 1));
            }
        }
        return builder.toString();
    }

    private String printResult(List<Territory> result) {
        StringBuilder builder = new StringBuilder();
        builder.append(getListTitle("RESULT"));
        if (result.isEmpty()) {
            builder.append(CommonLocalizator.getLocalizedText("NO_RESULT"));
        }
        for (Territory territory : result) {
            builder.append(territory);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public String getListTitle(String title) {
        return CommonLocalizator.getLocalizedText(title) + ": " + System.lineSeparator();
    }
}
