package model.territory;

import java.util.Comparator;

public class ComparatorByLevel implements Comparator<Territory> {

    @Override
    public int compare(Territory o1, Territory o2) {
        return o1.getLevel() - o2.getLevel();
    }
}