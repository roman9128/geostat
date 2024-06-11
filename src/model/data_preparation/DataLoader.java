package model.data_preparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public abstract class DataLoader {

    public DataLoader() {
    }

    void loadData(File file) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                try {
                    String[] data = scanner.nextLine().split(";");
                    addInfoAboutTerritory(data);
                } catch (Exception e) {
                    System.out.println("There are wrong data in datafile");
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    abstract void addInfoAboutTerritory(String[] data);
}