package model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.territory.Territory;
import model.territory.comparators.ComparatorByLevel;
import model.territory.comparators.ComparatorByName;

public class TerritorySorter implements Iterable<Territory> {
    private List<Territory> sortedTerritories;

    public TerritorySorter() {
    }

    public List<Territory> sortTerritories(HashMap<String, Territory> territoriesToSort) {
        sortedTerritories = new ArrayList<>();
        mapToList(territoriesToSort);
        sortByName();
        return sortedTerritories;
    }

    @Override
    public Iterator<Territory> iterator() {
        return new TerritoryIterator(sortedTerritories);
    }

    private void sortByName() {
        sortedTerritories.sort(new ComparatorByName());
    }

    private void mapToList(HashMap<String, Territory> territoriesToSort) {
        List<Territory> tempList = new ArrayList<>();
        for (HashMap.Entry<String, Territory> entry : territoriesToSort.entrySet()) {
            tempList.add(entry.getValue());
        }
        tempList.sort(new ComparatorByLevel());
        for (Territory territory : tempList) {
            if (territory.getLevel() == tempList.get(0).getLevel()) {
                sortedTerritories.add(territory);
            }
        }
    }
}