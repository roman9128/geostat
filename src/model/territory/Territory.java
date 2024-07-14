package model.territory;

import java.util.HashMap;

import model.types.DataType;

public class Territory {

    private String name;
    private int level;
    private TerritoryType type;
    private String[] capital;
    private HashMap<String, String> subunits;

    private long square;
    private long population;

    // private HashMap<String, Long> numericalData;

    public Territory(String name, int level, TerritoryType type, String[] capital,
            HashMap<String, String> subunits, long square, long population, long userData1, long userData2,
            long userData3) {
        this.name = name;
        this.level = level;
        this.type = type;
        this.capital = capital;
        this.subunits = subunits;
        this.square = square;
        this.population = population;
    }

    public Territory(String name, TerritoryType type, String[] capital, long square, long population) {
        this.name = name;
        this.type = type;
        this.capital = capital;
        this.square = square;
        this.population = population;
    }

    public long getNumericalInfoByDataType(DataType type) {
        long result = 0;
        if (type.equals(DataType.area)) {
            result = getSquare();
        }
        if (type.equals(DataType.population)) {
            result = getPopulation();
        }
        if (type.equals(DataType.level)) {
            result = getLevel();
        }
        return result;
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

    public String[] getCapital() {
        return capital;
    }

    public void setCapital(String capitalID, String capitalName) {
        if (capital == null) {
            capital = new String[2];
            capital[0] = capitalID;
            capital[1] = capitalName;
        }
    }

    public HashMap<String, String> getSubunits() {
        return subunits;
    }

    public void setSubunit(String subunitID, String subunitName) {
        if (subunits == null) {
            subunits = new HashMap<String, String>();
        }
        this.subunits.put(subunitID, subunitName);
    }

    public long getSquare() {
        return square;
    }

    public void setSquare(long square) {
        this.square = square;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("level: ");
        builder.append(level);
        builder.append(", name: ");
        builder.append(name);
        if (type == TerritoryType.Country) {
            builder.append(" (");
            builder.append(type);
            builder.append(")");
        } else {
            builder.append(" ");
            builder.append(type);
        }
        if (capital != null) {
            builder.append(", capital: ");
            builder.append(capital[1]);
        } else {
            builder.append("");
        }
        builder.append(", area: ");
        builder.append(square);
        builder.append(", population: ");
        builder.append(population);
        builder.append(", subunits: ");
        if (subunits == null) {
            builder.append("no information");
        } else {
            builder.append(subunits.values());
        }
        builder.append("\n");
        return builder.toString();
    }
}