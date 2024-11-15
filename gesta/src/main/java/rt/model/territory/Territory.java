package rt.model.territory;

import java.util.HashMap;

public class Territory {

    private String name;
    private int level;
    private TerritoryType type;
    private HashMap<String, Territory> capital;
    private HashMap<String, Territory> subunits;
    private HashMap<String, Long> numericalData;

    public Territory(String name, TerritoryType type, HashMap<String, Territory> capital) {
        this.name = name;
        this.type = type;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public TerritoryType getType() {
        return type;
    }

    public void setType(TerritoryType type) {
        this.type = type;
    }

    public HashMap<String, Territory> getCapital() {
        return capital;
    }

    public String getCapitalID() {
        String capitalID = capital.keySet().toString();
        capitalID = capitalID.replaceAll("\\,|\\[|\\]|\\s", "");
        return capitalID;
    }

    public String getCapitalName() {
        String capitalName = capital.get(getCapitalID()).getName();
        return capitalName;
    }

    public void setCapital(String capitalID, Territory capitalTerritory) {
        if (capital == null) {
            capital = new HashMap<String, Territory>();
        }
        this.capital.put(capitalID, capitalTerritory);
    }

    public HashMap<String, Territory> getSubunits() {
        return subunits;
    }

    public void setSubunit(String subunitID, Territory subunit) {
        if (subunits == null) {
            subunits = new HashMap<String, Territory>();
        }
        this.subunits.put(subunitID, subunit);
    }

    public HashMap<String, Long> getNumericalData() {
        return numericalData;
    }

    public void setNumericalData(String dataName, long dataValue) {
        if (numericalData == null) {
            numericalData = new HashMap<String, Long>();
        }
        this.numericalData.put(dataName, dataValue);
    }

    private String printNumericalData() {
        StringBuilder builder = new StringBuilder();
        for (HashMap.Entry<String, Long> entry : numericalData.entrySet()) {
            builder.append(System.lineSeparator());
            builder.append("\t");
            builder.append(entry.getKey());
            builder.append(": ");
            builder.append(entry.getValue());
        }
        return builder.toString();
    }

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
        builder.append("numerical data: ");
        if (numericalData == null) {
            builder.append("no information");
        } else {
            builder.append(printNumericalData());
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }
}