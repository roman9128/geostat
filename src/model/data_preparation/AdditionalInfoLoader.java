package model.data_preparation;

import java.io.File;

import model.map.Map;

public class AdditionalInfoLoader extends DataLoader {
    Map map;

    public AdditionalInfoLoader(Map map, File file) {
        this.map = map;
        loadData(file);
    }

    public Map sendMap() {
        return map;
    }

    @Override
    void addInfoAboutTerritory(String[] dataNames, String[] data) {
        if (dataNames.length > data.length) {
            String[] dataSubstitute = new String[dataNames.length];
            for (int i = 0; i < data.length; i++) {
                dataSubstitute[i] = data[i];
            }
            data = dataSubstitute;
        }
        for (int i = 1; i < dataNames.length; i++) {
            if (data[i] != null) {
                map.getMapAsHashMap().get(data[0]).setNumericalData(dataNames[i], Long.parseLong(data[i]));
            } else {
                map.getMapAsHashMap().get(data[0]).setNumericalData(dataNames[i], 0);
            }
        }
    }
}