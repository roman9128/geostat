package rt.model.territory;

import java.util.HashMap;

import rt.model.enums.TerritoryType;

public class Territory {

    private String name;
    private int level;
    private TerritoryType type;
    private HashMap<String, Territory> capital;
    private HashMap<String, Territory> subunits;
    private HashMap<String, Long> numericData;
    private HashMap<String, String> textInfo;

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

    private String printNumericData() {
        StringBuilder builder = new StringBuilder();
        for (HashMap.Entry<String, Long> entry : numericData.entrySet()) {
            builder.append(System.lineSeparator());
            builder.append("\t");
            builder.append(entry.getKey());
            builder.append(": ");
            builder.append(entry.getValue());
        }
        return builder.toString();
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

    private String printTextData() {
        StringBuilder builder = new StringBuilder();
        for (HashMap.Entry<String, String> entry : textInfo.entrySet()) {
            builder.append(System.lineSeparator());
            builder.append("\t");
            builder.append(entry.getKey());
            builder.append(": ");
            builder.append(entry.getValue());
        }
        return builder.toString();
    }

    // endregion
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("name: ");
        builder.append(name);
        if (capital != null) {
            builder.append(",");
            builder.append(System.lineSeparator());
            builder.append("capital: ");
            builder.append(getCapitalName());
        } else {
            builder.append("");
        }
        builder.append(",");
        builder.append(System.lineSeparator());
        builder.append("numeric data: ");
        if (numericData == null) {
            builder.append("no information");
        } else {
            builder.append(printNumericData());
        }
        builder.append(System.lineSeparator());
        builder.append("text info: ");
        // String res = (textInfo.isEmpty()) ? "no information" : textInfo.toString();
        if (textInfo == null) {
            builder.append("no information");
        } else {
            builder.append(printTextData());
        }

        builder.append(System.lineSeparator());
        return builder.toString();
    }
}
