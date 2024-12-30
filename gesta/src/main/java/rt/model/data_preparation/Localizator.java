package rt.model.data_preparation;

import java.util.HashMap;

public class Localizator extends XLSXDataLoader {
    private HashMap<String, String> translation;

    public Localizator(String localizationFile) {
        translation = new HashMap<>();
        loadData(localizationFile, false);
    }

    @Override
    public void sendData(String[] data) {
        translation.put(data[0], data[1]);
    }

    public HashMap<String, String> getLocalization() {
        return translation;
    }

    @Override
    protected void sendTitle(String[] title) {
        //
    }
}