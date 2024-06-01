import java.util.HashMap;

public class Service {
    private Map map;
    private IDbuilder idbuilder;

    public Service() {
        map = new Map();
        idbuilder = new IDbuilder();
    }

    public void addTerritory(String name, int level, TerritoryType type, Territory belongsToCountry, Territory belongsToRegion, Territory belongsToMunicipal, boolean isCapital) {
        Territory territory = new Territory(name, level, type, belongsToCountry, belongsToRegion, belongsToMunicipal, isCapital);
        String id = idbuilder.makeID(territory);
        territory.setId(id);
        map.addToMap(id, territory);
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Список: \n");
        for (HashMap.Entry<String, Territory> territory : map.getMap().entrySet()) {
            builder.append(territory.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }
}
