package rt.model.localizator;

import java.util.HashMap;

import rt.model.data_preparation.XLSXDataLoader;

/**
 * Класс для подготовки показываемого пользователю текста исходя из выбранного
 * языка (при наличии соответствующего файла локализации). Локализованный текст
 * хранится в статичном HashMap для доступа к нему из любого места программы без
 * необходимости создания экземпляра данного класса.
 */
public class CommonLocalizator extends XLSXDataLoader {

    /**
     * Хранилище для переведённого текста. Ключ - id текста, значение - сам
     * текст на выбранном языке.
     */
    private static HashMap<String, String> localizedText;

    /**
     * Конструктор, который вызывает методы загрузки данных из XLSX-файлов:
     * первый файл с наименованиями территорий, второй - с программным служебным
     * текстом.
     *
     * @param language выбранный язык
     */
    public CommonLocalizator(String language) {
        localizedText = new HashMap<>();
        loadData(language + "names.xlsx", false);
        loadData(language + "text.xlsx", false);
    }

    /**
     * Метод получения текста по ключу. Если текст по указанному ключу
     * отсутствует, возвращается сам запрошенный ключ.
     *
     * @param id ключ в HashMap<String, String> localizedText
     * @return локализованный текст или ключ при отсутствии текста по нему
     */
    public static String getLocalizedText(String id) {
        if (localizedText.get(id) == null) {
            return id;
        } else {
            return localizedText.get(id);
        }
    }

    /**
     * Запись полученной информации localizedText.
     */
    @Override
    protected void setData(String[] data) {
        localizedText.put(data[0], data[1]);
    }

    @Override
    protected void setTitle(String[] title) {
        // метод не используется. унаследован от родительского класса
    }
}
