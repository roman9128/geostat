package rt.model.localizator;

import java.util.HashMap;

import rt.model.data_preparation.XLSXDataLoader;

public class CommonLocalizator extends XLSXDataLoader {

    private static HashMap<String, String> localizedText;

    public CommonLocalizator(String language) {
        localizedText = new HashMap<>();
        loadData(language + "names.xlsx", false);
        loadData(language + "text.xlsx", false);
    }

    public static String getLocalizedText(String id) {
        if (localizedText.get(id) == null) {
            return id;
        } else {
            return localizedText.get(id);
        }
    }

    @Override
    protected void setData(String[] data) {
        localizedText.put(data[0], data[1]);
    }

    @Override
    protected void setTitle(String[] title) {
        //
    }
}
