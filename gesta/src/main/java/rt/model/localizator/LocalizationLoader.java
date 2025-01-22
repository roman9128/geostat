package rt.model.localizator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс для вызова загрузчика локализованного текста.
 */
public class LocalizationLoader {

    /**
     * Файл для хранения информации о выбранном пользователем языке.
     */
    private final File language = new File("language.txt");

    /**
     * При создании экземпляра этого класса он вызывает создание общего
     * локализатора и наполнение его статического HashMap<String, String>
     * localizedText данными из XLSX-файлов.
     *
     * @param languageFile файл, где хранится запись о выбранном языке, при его
     * отсутствии используется русский язык по умолчанию
     */
    public LocalizationLoader() {
        String currentLanguage;
        try (Scanner scanner = new Scanner(language)) {
            currentLanguage = scanner.nextLine();
        } catch (FileNotFoundException e) {
            currentLanguage = "rus";
        }
        new CommonLocalizator(currentLanguage);
    }
}
