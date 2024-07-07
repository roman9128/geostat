package model.data_preparation;

import java.io.File;
import java.util.HashMap;

public class Localizator extends DataLoader {
    HashMap<String, String> names;

    public Localizator() {
        names = new HashMap<>();
        loadData(new File("ru.txt"));
    }

    @Override
    void addInfoAboutTerritory(String[] data) {
        names.put(data[0], data[1]);
    }

    public HashMap<String, String> getLocalization() {
        return names;
    }
}