import java.util.HashMap;
import java.util.Iterator;

public class TerritoryIterator implements Iterator<Territory> {
    private int index;
    private HashMap<String, Territory> map;

    public TerritoryIterator(HashMap<String, Territory> map) {
        this.map = map;
    }

    @Override
    public boolean hasNext() {
        return map.size() > index;
    }

    @Override
    public Territory next() {
        return map.get(index++);
    }
}
