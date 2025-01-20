package rt.model.territory.comparators;

import java.util.Comparator;

import rt.model.territory.Territory;

/**
 * Компаратор по уровню территории
 */
public class ComparatorByLevel implements Comparator<Territory> {

    @Override
    public int compare(Territory o1, Territory o2) {
        return o1.getLevel() - o2.getLevel();
    }
}
