package rt.model.territory;

import java.util.HashMap;

import rt.model.enums.TerritoryType;
import rt.model.localizator.CommonLocalizator;
import rt.model.printer.HashMapPrinter;

/**
 * Класс основных объектов программы, применяемый для описания
 * административно-территориальных единиц
 */
public class Territory {

    private String name;
    private int level;
    private TerritoryType type;
    private HashMap<String, Territory> capital; // столица обычно одна, HashMap используется для удобства
    private HashMap<String, Territory> subunits; // административно-территориальные единицы, входящие в состав территории
    private HashMap<String, Long> numericData; // числовая информация
    private HashMap<String, String> textInfo; // текстовая информация

    public Territory(String name, TerritoryType type, HashMap<String, Territory> capital) {
        this.name = name;
        this.type = type;
        this.capital = capital;
    }

    // region name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // endregion

    // region level
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    // endregion

    // region terrType
    public TerritoryType getType() {
        return type;
    }

    public void setType(TerritoryType type) {
        this.type = type;
    }
    // endregion

    // region capital
    public HashMap<String, Territory> getCapital() {
        return capital;
    }

    public String getCapitalID() {
        return capital.keySet().toString().replaceAll("\\,|\\[|\\]|\\s", "");
    }

    public String getCapitalName() {
        return capital.get(getCapitalID()).getName();
    }

    public void setCapital(String capitalID, Territory capitalTerritory) {
        if (capital == null) {
            capital = new HashMap<>();
        }
        this.capital.put(capitalID, capitalTerritory);
    }
    // endregion

    // region subunits
    public HashMap<String, Territory> getSubunits() {
        return subunits;
    }

    public void setSubunit(String subunitID, Territory subunit) {
        if (subunits == null) {
            subunits = new HashMap<>();
        }
        this.subunits.put(subunitID, subunit);
    }
    // endregion

    // region numeric info
    public HashMap<String, Long> getNumericData() {
        return numericData;
    }

    public void setNumericData(String dataName, long dataValue) {
        if (numericData == null) {
            numericData = new HashMap<>();
        }
        this.numericData.put(dataName, dataValue);
    }
    // endregion

    // region text info
    public HashMap<String, String> getTextInfo() {
        return textInfo;
    }

    public void setTextInfo(String textID, String textValue) {
        if (textInfo == null) {
            textInfo = new HashMap<>();
        }
        this.textInfo.put(textID, textValue);
    }

    // endregion
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(CommonLocalizator.getLocalizedText("NAME"))
                .append(": ")
                .append(name);
        if (capital.get(getCapitalID()) != null) {
            builder.append(System.lineSeparator())
                    .append(CommonLocalizator.getLocalizedText("CAPITAL"))
                    .append(": ")
                    .append(getCapitalName());
        } else {
            builder.append("");
        }
        builder.append(System.lineSeparator())
                .append(CommonLocalizator.getLocalizedText("NUMERIC"))
                .append(": ");
        if (numericData == null) {
            builder.append(CommonLocalizator.getLocalizedText("NO_INFO"));
        } else {
            builder.append(HashMapPrinter.printHashMap(numericData));
        }
        builder.append(System.lineSeparator())
                .append(CommonLocalizator.getLocalizedText("TEXT"))
                .append(": ");
        if (textInfo == null) {
            builder.append(CommonLocalizator.getLocalizedText("NO_INFO"));
        } else {
            builder.append(HashMapPrinter.printHashMap(textInfo));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
