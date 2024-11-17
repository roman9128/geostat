package rt.model.data_preparation;

import java.io.File;
import java.util.HashMap;

import rt.model.enums.TerritoryType;
import rt.model.map.Map;
import rt.model.territory.Territory;

public class MapCreator extends DataLoader {

    private Map loadedMap;
    private HashMap<String, String> localizedNames;

    public MapCreator(String idTypeCapital, String names) {
        loadedMap = new Map();
        localizedNames = new Localizator(names).getLocalization();
        loadData(new File(idTypeCapital));
        organize();
    }

    public Map sendMap() {
        return loadedMap;
    }

    private void organize() {
        new MapOrganizer().organize();
    }

    @Override
    protected void sendInfoFrom(String[] data) {
        addTerritory(sendID(data), sendName(data), sendTerritoryType(data), sendCapital(data));
    }

    private void addTerritory(String id, String name, TerritoryType type, HashMap<String, Territory> capital) {
        loadedMap.addToMap(id, new Territory(name, type, capital));
    }

    private String sendID(String[] data) {
        return data[0];
    }

    private String sendName(String[] data) {
        return localizedNames.get(data[0]);
    }

    private TerritoryType sendTerritoryType(String[] data) {
        return TerritoryType.valueOf(data[1]);
    }

    private HashMap<String, Territory> sendCapital(String[] data) {
        if (!data[2].equals("0")) {
            HashMap<String, Territory> capital = new HashMap<>();
            capital.put(data[2], null);
            return capital;
        }
        return null;
    }

    private class MapOrganizer {

        private MapOrganizer() {
        }

        private void organize() {
            for (HashMap.Entry<String, Territory> entry : loadedMap.getMapAsHashMap().entrySet()) {
                setTerritoryLevel(entry);
                setCapital(entry);
                setSubunits(entry);
            }
        }

        private void setTerritoryLevel(HashMap.Entry<String, Territory> entry) {
            if (entry.getKey().length() < 4) {
                entry.getValue().setLevel(1);
            } else {
                String[] level = entry.getKey().split("_");
                entry.getValue().setLevel(level.length);
            }
        }

        private void setCapital(HashMap.Entry<String, Territory> entry) {
            try {
                entry.getValue().setCapital(entry.getValue().getCapitalID(),
                        loadedMap.getTerritoryOnID(entry.getValue().getCapitalID()));
            } catch (Exception e) {

            }
        }

        private void setSubunits(HashMap.Entry<String, Territory> entry) {
            if (entry.getKey().length() > 3) {
                String[] ids = entry.getKey().split("_");
                for (int i = 1; i < ids.length; i++) {
                    ids[i] = ids[i - 1] + "_" + ids[i];
                }
                for (int i = 0; i < ids.length - 1; i++) {
                    loadedMap.getTerritoryOnID(ids[i]).setSubunit(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}