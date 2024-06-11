package model;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import model.data_preparation.AdditionalInfoLoader;
import model.data_preparation.BasicInfoLoader;
import model.data_preparation.MapOrganizer;
import model.map.Map;
import model.map.TerritorySorter;
import model.territory.SubunitsViewer;
import model.territory.Territory;

public class Service {
    private Map map;

    public Service() {
        map = new Map();
        prepareTheMap("fd.csv");
        addAdditionalInfo(map, "ain.csv");
    }

    public Map addAdditionalInfo(Map map, String path) {
        File file = new File(path);
        AdditionalInfoLoader loader = new AdditionalInfoLoader(map, file);
        return loader.sendMap();
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Common list: \n");
        for (HashMap.Entry<String, Territory> territory : map.getMap().entrySet()) {
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
            builder.append("subunits: ");
            builder.append(getSubunits(territory));
            builder.append("\n");
        }
        return builder.toString();
    }

    private String getSubunits(Territory territory) {
        SubunitsViewer viewer = new SubunitsViewer();
        return viewer.showSubunits(map, territory);
    }

    private void prepareTheMap(String path) {
        File file = new File(path);
        map.setMap(getMapFromFile(file));
        organize(map);
    }

    private HashMap<String, Territory> getMapFromFile(File file) {
        BasicInfoLoader loader = new BasicInfoLoader(file);
        return loader.sendMap();
    }

    private Map organize(Map map) {
        MapOrganizer mapOrganizer = new MapOrganizer();
        return mapOrganizer.organize(map);
    }
}
