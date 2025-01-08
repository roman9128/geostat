package rt.model.localizator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LocalizationLoader {

    public LocalizationLoader(File languageFile) {
        String currentLanguage;
        try (Scanner scanner = new Scanner(languageFile)) {
            currentLanguage = scanner.nextLine();
        } catch (FileNotFoundException e) {
            currentLanguage = "rus";
        }
        new CommonLocalizator(currentLanguage);
    }
}