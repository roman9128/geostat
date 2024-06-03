package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import model.map.Map;
import model.territory.Territory;
import model.territory.TerritoryType;

public class Service {
    private Map map;

    public Service() {
        map = new Map();
        loadData();
    }

    public String getList() {
        StringBuilder builder = new StringBuilder();
        builder.append("Список: \n");
        for (HashMap.Entry<String, Territory> territory : map.getMap().entrySet()) {
            builder.append(territory.getKey());
            builder.append(": ");
            builder.append(territory.getValue());
            builder.append("\n");
        }
        return builder.toString();
    }

    private void loadData() {
        try {
            Scanner scanner = new Scanner(new File("st.csv"));
            while (scanner.hasNextLine()) {
                try {
                    String[] data = scanner.nextLine().split(";");
                    addTerritory(data);
                } catch (Exception e) {
                    System.out.println("There are wrong data in datafile");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addTerritory(String[] data) {
        addTerritory(sendID(data), sendLevel(data), sendName(data), sendTerritoryType(data), sendCapital(data));
    }

    private void addTerritory(String id, int level, String name, TerritoryType type, boolean isCapital) {
        Territory territory = new Territory(id, level, name, type, isCapital);
        map.addToMap(id, territory);
    }

    private String sendID(String[] data) {
        return data[0];
    }

    private int sendLevel(String[] data) {
        return Integer.parseInt(data[1]);
    }

    private String sendName(String[] data) {
        return data[2];
    }

    private TerritoryType sendTerritoryType(String[] data) {
        return TerritoryType.valueOf(data[3]);
    }

    private boolean sendCapital(String[] data) {
        if (data[4].equals("1")) {
            return true;
        }
        return false;
    }
}
