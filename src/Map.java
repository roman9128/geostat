import java.util.HashMap;
import java.util.Iterator;

public class Map implements Iterable<Territory> {
    private HashMap<String, Territory> map;
    // private UserData UserData1;
    // private UserData UserData2;
    // private UserData UserData3;
    // private UserData UserData4;
    // private UserData UserData5;

    public Map() {
        map = new HashMap<String, Territory>();
    }

    public void addToMap(String id, Territory territory) {
        map.put(id, territory);
    }

    @Override
    public Iterator<Territory> iterator() {
       return new TerritoryIterator(map);
    }
}
