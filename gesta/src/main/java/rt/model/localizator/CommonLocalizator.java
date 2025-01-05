package rt.model.localizator;

import java.util.HashMap;

import rt.model.data_preparation.XLSXDataLoader;

public class CommonLocalizator extends XLSXDataLoader {

    private final HashMap<String, String> localizedText;

    public CommonLocalizator(String language) {
        localizedText = new HashMap<>();
        loadData(language + "names.xlsx", false);
        loadData(language + "text.xlsx", false);
    }

    public String getLocalizedText(String id) {
        return localizedText.get(id);
    }

    @Override
    protected void sendData(String[] data) {
        localizedText.put(data[0], data[1]);
    }

    @Override
    protected void sendTitle(String[] title) {
        //
    }
}