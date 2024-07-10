package model.territory;

import java.util.HashMap;

import model.types.DataType;

public class Territory {

    private String id;
    private String name;
    private int level;
    private TerritoryType type;
    private boolean isCapital;
    private HashMap<String, String> subunits;

    private long square;
    private long population;

    private long userData1;
    private long userData2;
    private long userData3;

    public Territory(String id, String name, int level, TerritoryType type, boolean isCapital,
            HashMap<String, String> subunits, long square, long population, long userData1, long userData2,
            long userData3) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type = type;
        this.isCapital = isCapital;
        this.subunits = subunits;
        this.square = square;
        this.population = population;
        this.userData1 = userData1;
        this.userData2 = userData2;
        this.userData3 = userData3;

    }

    public Territory(String id, String name, TerritoryType type, boolean isCapital, long square, long population) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.isCapital = isCapital;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean isCapital) {
        this.isCapital = isCapital;
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

    public long getUserData1() {
        return userData1;
    }

    public void setUserData1(long userData1) {
        this.userData1 = userData1;
    }

    public long getUserData2() {
        return userData2;
    }

    public void setUserData2(long userData2) {
        this.userData2 = userData2;
    }

    public long getUserData3() {
        return userData3;
    }

    public void setUserData3(long userData3) {
        this.userData3 = userData3;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("id: ");
        builder.append(id);
        builder.append(", level: ");
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
        if (isCapital) {
            builder.append(", it's a capital");
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