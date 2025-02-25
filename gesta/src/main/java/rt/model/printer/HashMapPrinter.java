package rt.model.printer;

import java.util.HashMap;

import rt.model.territory.Territory;

/**
 * Класс для оформления вывода содержимого HashMap.
 */
public class HashMapPrinter {

    /**
     * Вывод всего содержимого.
     */
    public static <K, V> String printHashMap(HashMap<K, V> data) {
        StringBuilder builder = new StringBuilder();
        for (HashMap.Entry<K, V> entry : data.entrySet()) {
            builder.append(System.lineSeparator());
            builder.append("\t");
            builder.append(entry.getKey());
            builder.append(": ");
            builder.append(entry.getValue());
        }
        return builder.toString();
    }

    /**
     * Вывод только информации о наименовании территорий.
     */
    public static String printTerritoryNames(HashMap<String, Territory> territories) {
        StringBuilder builder = new StringBuilder();
        for (HashMap.Entry<String, Territory> entry : territories.entrySet()) {
            builder.append(System.lineSeparator());
            builder.append("\t");
            builder.append(entry.getValue().getName());
        }
        return builder.toString();
    }
}
