package model.territory;

import java.util.HashMap;

import model.user_data.UserData;

public class Territory {

    private String id;
    private String name;
    private int level;
    private TerritoryType type;
    private boolean isCapital;
    private HashMap<String, Territory> subunits;

    private long square;

    @SuppressWarnings("unused")
    private UserData UserData1;
    @SuppressWarnings("unused")
    private UserData UserData2;
    @SuppressWarnings("unused")
    private UserData UserData3;
    @SuppressWarnings("unused")
    private UserData UserData4;
    @SuppressWarnings("unused")
    private UserData UserData5;

    public Territory(String id, String name, int level, TerritoryType type, boolean isCapital,
            HashMap<String, Territory> subunits, long square, UserData userData1, UserData userData2,
            UserData userData3, UserData userData4, UserData userData5) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.type = type;
        this.isCapital = isCapital;
        this.subunits = subunits;
        this.square = square;
        UserData1 = userData1;
        UserData2 = userData2;
        UserData3 = userData3;
        UserData4 = userData4;
        UserData5 = userData5;
    }

    public Territory(String id, String name, TerritoryType type) {
        this.id = id;
        this.name = name;
        this.type = type;
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

    public HashMap<String, Territory> getSubunits() {
        return subunits;
    }

    public void setSubunits(HashMap<String, Territory> subunits) {
        this.subunits = subunits;
    }

    public long getSquare() {
        return square;
    }

    public void setSquare(long square) {
        this.square = square;
    }

    @Override
    public String toString() {
        return "[id: " + id + ", name: " + name + ", type: " + type + "]";
    }
}