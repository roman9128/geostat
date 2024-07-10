package model;

import java.io.File;
import java.util.HashMap;
import java.util.List;

// import model.data_preparation.AdditionalInfoLoader;
import model.data_preparation.BasicInfoLoader;
import model.map.Map;
import model.map.TerritorySorter;
import model.territory.Territory;
import model.territory.TerritoryType;
import model.types.DataType;
import model.types.Operator;

public class Service {
    private Map map;

    public Service() {
        map = getPreparedMap("fd.csv");
        // addAdditionalInfo("ain.csv");
    }

    public String findByName(String name) {
        List<Territory> result = map.findByName(name);
        return printResult(result);
    }

    public String findByParameter(TerritoryType type) {
        List<Territory> result = map.findByTerritoryType(type);
        return printResult(result);
    }

    public String findByParameter(DataType type, Operator operator, long number) {
        List<Territory> result = map.findByParameter(type, operator, number);
        return printResult(result);        
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Common list: \n");
        for (HashMap.Entry<String, Territory> territory : map.getMapAsHashMap().entrySet()) {
            builder.append(territory.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }

    public String getSortedList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Common list: \n");
        TerritorySorter sorter = new TerritorySorter(map);
        List<Territory> listToShow = sorter.getTerritories();
        for (Territory territory : listToShow) {
            builder.append("\n");
            builder.append(territory);
            builder.append("\n");
        }
        return builder.toString();
    }

    private Map getPreparedMap(String path) {
        return new BasicInfoLoader(new File(path)).sendMap();
    }

    private String printResult (List<Territory> result){
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

    // private Map addAdditionalInfo(String path) {
    // return new AdditionalInfoLoader(map, new File(path)).sendMap();
    // }
}