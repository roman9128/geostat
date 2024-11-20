package rt.model.localizator;

import java.util.HashMap;

public class Localizator {

    private static HashMap<String, String> terrNames;
    private static HashMap<String, String> text;

    public Localizator() {
        terrNames = new HashMap<>();
        text = new HashMap<>();
    }

    public static String getTerrName(String id) {
        return terrNames.get(id);
    }

    public static String getText(String textID) {
        return text.get(textID);
    }

    


}