package rt.model.map;

import java.util.Iterator;
import java.util.List;

import rt.model.territory.Territory;

/**
 * Итератор объектов класса Territory.
 */
public class TerritoryIterator implements Iterator<Territory> {

    private int i;
    private List<Territory> territories;

    public TerritoryIterator(List<Territory> territories) {
        this.territories = territories;
    }

    @Override
    public boolean hasNext() {
        return territories.size() > i;
    }

    @Override
    public Territory next() {
        return territories.get(i++);
    }
}
