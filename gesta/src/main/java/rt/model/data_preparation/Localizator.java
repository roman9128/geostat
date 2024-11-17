package rt.model.data_preparation;

import java.util.HashMap;

public class Localizator extends XLSXDataLoader {
    private HashMap<String, String> translation;

    public Localizator(String localizationFile) {
        translation = new HashMap<>();
        loadData(localizationFile, false);
    }

    @Override
    public void sendData(Object[] data) {
        translation.put(data[0].toString(), data[1].toString());
    }

    public HashMap<String, String> getLocalization() {
        return translation;
    }

    @Override
    protected void sendTitle(Object[] title) {
        //
    }
}