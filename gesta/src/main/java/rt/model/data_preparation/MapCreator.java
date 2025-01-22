package rt.model.data_preparation;

import java.util.HashMap;

import rt.model.enums.TerritoryType;
import rt.model.localizator.CommonLocalizator;
import rt.model.map.Map;
import rt.model.territory.Territory;

/**
 * Класс для создания объектов класса Territory из данных, содержащихся в
 * XLSX-файле. Для создания объектов указанного класса требуется id, тип и
 * ссылка на столицу территории(при наличии).
 */
public class MapCreator extends XLSXDataLoader {

    /**
     * Карта с загруженной информацией.
     */
    private Map loadedMap;

    /**
     * Конструктор, создающий объект класса Map (карта), содержащий в себе
     * информацию об объектах класса Territory.
     *
     * @param idTypeCapital ссылка на наименование файла с требуемыми данными.
     */
    public MapCreator(String idTypeCapital) {
        loadedMap = new Map();
        loadData(idTypeCapital, false);
        organize();
    }

    /**
     * Метод, возвращающий загруженную карту с упорядоченной информацией о
     * территориях.
     *
     * @return загруженная карта с упорядоченной информацией о территориях
     */
    public Map sendMap() {
        return loadedMap;
    }

    /**
     * Метод, вызываемый для организации информации о территориях.
     */
    private void organize() {
        new MapOrganizer().organize();
    }

    /**
     * Во избежание ошибок при формировании входящего массива предполагается,
     * что файл c соответствующей информацией будет закрыт для редактирования
     * пользователями. Метод для получения информации из загрузчика и передачи
     * полученной информацию метод, создающий объекты класса Territory.
     */
    @Override
    protected void setData(String[] data) {
        addTerritory(sendID(data), sendName(data), sendTerritoryType(data), sendCapital(data));
    }

    /**
     * Метод создания и добавления новой территории на карту в HashMap на
     * основании имеющейся информации.
     *
     * @param id уникальный идентификатор территории
     * @param name наименование территории на выбранном при запуске программы
     * языке (по умолчанию на русском)
     * @param type тип территории из числа указанных в соответствующем enum
     * @param capital столица территории
     */
    private void addTerritory(String id, String name, TerritoryType type, HashMap<String, Territory> capital) {
        loadedMap.addToMap(id, new Territory(name, type, capital));
    }

    /**
     * Метод извлечения id территории из массива.
     *
     * @param data массив с данными
     * @return String id, первый элемент массива
     */
    private String sendID(String[] data) {
        return data[0];
    }

    /**
     * Метод формирования имени территории, который извлекает имя по id
     * территории из коллекции локализованных имён. Если имя по id найти не
     * удаётся, возвращается сам id с припиской.
     *
     * @param data массив с данными
     * @return String имя территории
     */
    private String sendName(String[] data) {
        if (CommonLocalizator.getLocalizedText(data[0]) == null) {
            return data[0] + "-id";
        } else {
            return CommonLocalizator.getLocalizedText(data[0]);
        }
    }

    /**
     * Метод, возвращающий тип территории из соответствующего enum.
     *
     * @param data массив с данными
     * @return тип территории
     */
    private TerritoryType sendTerritoryType(String[] data) {
        return TerritoryType.valueOf(data[1]);
    }

    /**
     * Метод для формирования информации о столице территории. Если информации о
     * столице нет, то в таблице соответствующая ячейка должна быть заполнена
     * каким-либо произвольным значением, например "no". Создаётся HashMap, в
     * качестве ключа которого указывается id столицы, значение же определяется
     * при последующей обработке.
     *
     * @param data массив с данными
     * @return HashMap<String, Territory> с информацией о столице или null, если
     * такой информации нет
     */
    private HashMap<String, Territory> sendCapital(String[] data) {
        HashMap<String, Territory> capital = new HashMap<>();
        capital.put(data[2], null);
        return capital;

    }

    @Override
    protected void setTitle(String[] title) {
        // метод не используется. унаследован от родительского класса
    }

    /**
     * Вложенный класс для организации информации о территориях.
     */
    private class MapOrganizer {

        private MapOrganizer() {
        }

        /**
         * Метод, итерирующий элементы HashMap с территориями и отправляющий
         * каждую из них на дальнейшую обработку.
         */
        private void organize() {
            for (HashMap.Entry<String, Territory> entry : loadedMap.getMapAsHashMap().entrySet()) {
                setTerritoryLevel(entry);
                setCapital(entry);
                setSubunits(entry);
            }
        }

        /**
         * Метод для определения и установления условного уровня в
         * административно-территориальной системе страны соответствующей
         * территории исходя из длины её id.
         *
         * @param entry элемент HashMap с информацией о конкретной территории
         */
        private void setTerritoryLevel(HashMap.Entry<String, Territory> entry) {
            if (entry.getKey().length() < 4) {
                entry.getValue().setLevel(1);
            } else {
                String[] level = entry.getKey().split("_");
                entry.getValue().setLevel(level.length);
            }
        }

        /**
         * Метод для установления в объекте класса Territory информации о
         * столице по её id. Добавляет объект класса Territory в HashMap,
         * касающийся столицы, у которого ранее был определён ключ.
         *
         * @param entry элемент HashMap с информацией о конкретной территории
         */
        private void setCapital(HashMap.Entry<String, Territory> entry) {
            Territory territory = entry.getValue();
            if (loadedMap.getMapAsHashMap().containsKey(territory.getCapitalID())) { // проверка наличия в коллекции территории с указанным id
                territory.setCapital(
                        territory.getCapitalID(), // id столицы был определён ранее
                        loadedMap.getTerritoryOnID(territory.getCapitalID()));
            }
        }

        /**
         * Метод определения территорий, входящих в состав других территорий
         * исходя из их id, и включения их в перечень подчинённых
         * административно-территориальнх единиц. Например, устанавливает, что
         * какие-то районы входят в состав определённой области.
         *
         * @param entry элемент HashMap с информацией о конкретной территории
         */
        private void setSubunits(HashMap.Entry<String, Territory> entry) {
            if (entry.getKey().length() > 3) { // определяет, что соответствующая территория является подчинённой
                String[] ids = entry.getKey().split("_"); // формирование массива id территорий вышестоящего уровня, в состав которых входит соответствующая территория
                for (int i = 1; i < ids.length; i++) {
                    ids[i] = ids[i - 1] + "_" + ids[i];
                }
                for (int i = 0; i < ids.length - 1; i++) {
                    loadedMap.getTerritoryOnID(ids[i]).setSubunit(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
