package model.territory.comparators;

import java.util.Comparator;

import model.territory.Territory;

public class ComparatorByName implements Comparator<Territory> {

    @Override
    public int compare(Territory o1, Territory o2) {
        return o1.getName().compareTo(o2.getName());
    }
}