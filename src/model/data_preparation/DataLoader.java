package model.data_preparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import model.territory.Territory;
import model.territory.TerritoryType;

public class DataLoader {
    private HashMap<String, Territory> loadedMap;

    public DataLoader(File file) {
        loadedMap = new HashMap<>();
        loadData(file);
    }

    public HashMap<String, Territory> sendMap() {
        return loadedMap;
    }

    private void loadData(File file) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
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
        addTerritory(sendID(data), sendName(data), sendTerritoryType(data), sendCapital(data));
    }

    private void addTerritory(String id, String name, TerritoryType type, boolean isCapital) {
        Territory territory = new Territory(id, name, type, isCapital);
        loadedMap.put(id, territory);
    }

    private String sendID(String[] data) {
        return data[0];
    }

    private String sendName(String[] data) {
        return data[1];
    }

    private TerritoryType sendTerritoryType(String[] data) {
        return TerritoryType.valueOf(data[2]);
    }

    private boolean sendCapital(String[] data) {
        if (data[3].equals("1")) {
            return true;
        }
        return false;
    }
}