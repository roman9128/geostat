package model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.territory.Territory;
import model.territory.comparators.ComparatorByLevel;
// import model.territory.comparators.ComparatorByName;

public class TerritorySorter implements Iterable<Territory> {
    private List<Territory> territories;

    public TerritorySorter(Map map) {
        territories = new ArrayList<>();
        mapToList(map);
    }

    public List<Territory> getTerritories() {
        sortByLevel();
        return territories;
    }

    @Override
    public Iterator<Territory> iterator() {
        return new TerritoryIterator(territories);
    }

    private void sortByLevel() {
        territories.sort(new ComparatorByLevel());
    }

    // private void sortByName() {
    //     territories.sort(new ComparatorByName());
    // }

    private void mapToList(Map map) {
        for (HashMap.Entry<String, Territory> entry : map.getMapAsHashMap().entrySet()) {
            territories.add(entry.getValue());
        }
    }
}