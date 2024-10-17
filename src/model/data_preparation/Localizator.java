package model.data_preparation;

import java.io.File;
import java.util.HashMap;

public class Localizator extends DataLoader {
    private HashMap<String, String> translation;

    public Localizator(File localizationFile) {
        translation = new HashMap<>();
        loadData(localizationFile);
    }

    @Override
    public void sendInfoFrom(String[] data) {
        translation.put(data[0], data[1]);
    }

    public HashMap<String, String> getLocalization() {
        return translation;
    }
}