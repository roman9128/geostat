package rt.model.data_preparation;

import java.util.HashMap;

public class Localizator extends XLSXDataLoader {
    private HashMap<String, String> translation;

    public Localizator(String localizationFile) {
        translation = new HashMap<>();
        loadData(localizationFile);
    }

    @Override
    public void send(String[] data) {
        translation.put(data[0], data[1]);
    }

    public HashMap<String, String> getLocalization() {
        return translation;
    }
}