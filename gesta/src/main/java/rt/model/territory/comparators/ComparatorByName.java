package rt.model.territory.comparators;

import java.util.Comparator;

import rt.model.territory.Territory;

/**
 * Компаратор по наименованию территории.
 */
public class ComparatorByName implements Comparator<Territory> {

    @Override
    public int compare(Territory o1, Territory o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
