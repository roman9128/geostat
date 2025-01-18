package rt.model.data_preparation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * Класс для загрузки данных из текстовых файлов. Использовался для загрузки из CSV-файлов до перехода на XLSX-файлы
 */
@Deprecated
abstract public class DataLoader {
/**
 * Метод загрузки данных из файла построчно с разбивкой считанной строки на отдельные слова, записываемые в массив
 * @param path путь к файлу
 */
    void loadData(String path) {
        File file = new File(path);
        try (Scanner scanner = new Scanner(file)) {
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                try {
                    String[] data = scanner.nextLine().split(";");
                    sendInfoFrom(data);
                } catch (Exception e) {
                    System.out.println("There are wrong data in datafile");
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
/**
 * 
 * Метод отправки в обработчик получившегося массива строк
 * @param data массив строк для обработки
 */
    abstract protected void sendInfoFrom(String[] data);
}