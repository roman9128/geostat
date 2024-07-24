package model.data_preparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import model.map.Map;

public class AdditionalInfoLoader {
    private Map map;

    public AdditionalInfoLoader(Map map, File file) {
        this.map = map;
        loadUserData(file);
    }

    public Map sendMap() {
        return map;
    }

    private void setUserDataNames(String[] text){
        String[] dataNamesToShow = new String[text.length-1];
        for (int i = 1; i < text.length; i++) {
            dataNamesToShow[i-1] = text[i];
        }
        map.setUserDataNames(dataNamesToShow);
    }

    private void loadUserData(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String[] dataNames = scanner.nextLine().split(";");
            setUserDataNames(dataNames);
            while (scanner.hasNextLine()) {
                try {
                    String[] data = scanner.nextLine().split(";");
                    addInfoAboutTerritory(dataNames, data);
                } catch (Exception e) {
                    System.out.println("There are wrong data in datafile");
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addInfoAboutTerritory(String[] dataNames, String[] data) {
        if (dataNames.length > data.length) {
            String[] dataSubstitute = new String[dataNames.length];
            for (int i = 0; i < data.length; i++) {
                dataSubstitute[i] = data[i];
            }
            data = dataSubstitute;
        }
        for (int i = 1; i < dataNames.length; i++) {
            if (data[i] != null) {
                map.getMapAsHashMap().get(data[0]).setNumericalData(dataNames[i], Long.parseLong(data[i]));
            } else {
                map.getMapAsHashMap().get(data[0]).setNumericalData(dataNames[i], 0);
            }
        }
    }
}