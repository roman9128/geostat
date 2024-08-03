package model.territory;

import java.util.HashMap;

public class Territory {

    private String name;
    private int level;
    private TerritoryType type;
    private HashMap<String, Territory> capital;
    private HashMap<String, Territory> subunits;
    private HashMap<String, Territory> structuredSubunits;
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

    public HashMap<String, Territory> getStructuredSubunit() {
        return structuredSubunits;
    }

    public void setStructuredSubunit(String subunitID, Territory subunit) {
        if (structuredSubunits == null) {
            structuredSubunits = new HashMap<String, Territory>();
        }
        this.structuredSubunits.put(subunitID, subunit);
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("level: ");
        builder.append(level);
        builder.append(", name: ");
        builder.append(name);
        // if (type == TerritoryType.Country) {
        // builder.append(" (");
        // builder.append(type);
        // builder.append(")");
        // } else {
        // builder.append(" ");
        // builder.append(type);
        // }
        if (capital != null) {
            builder.append(", capital: ");
            builder.append(capital);
        } else {
            builder.append("");
        }
        builder.append(", numerical data: ");
        if (numericalData == null) {
            builder.append("no information");
        } else {
            builder.append("\n");
            builder.append(numericalData.toString());
        }
        builder.append(", subunits: ");
        // if (subunits == null) {
        //     builder.append("no information");
        // } else {
        //     builder.append("\n");
        //     builder.append(subunits.values());
        // }
        if (structuredSubunits == null) {
            builder.append("no information");
        } else {
            builder.append("\n");
            builder.append(structuredSubunits.values());
        }
        builder.append("\n");
        return builder.toString();
    }
}