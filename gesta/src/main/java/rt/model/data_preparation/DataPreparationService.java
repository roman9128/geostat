package rt.model.data_preparation;

import rt.model.map.Map;

/**
 * Класс, организующий процесс загрузки данных из файлов, создания объектов
 * класса Territory и наполнения ими карты (объекта класса Map)
 */
public final class DataPreparationService {

    /**
     * Карта с загруженной информацией.
     */
    private Map loadedMap;
    private final String ID_TYPE_CAPITAL = "bd.xlsx";
    private final String NUMERICAL_INFO = "nd.xlsx";
    // private final String NUM_PROPORTION_INFO = "pd.csv";
    private final String TEXT_INFO = "td.xlsx";

    /**
     * Конструктор запускает процесс загрузки и подготовки данных сразу же при
     * создании объекта данного класса.
     */
    public DataPreparationService() {
        loadAndPrepareData();
    }

    /**
     * Метод для загрузки данных разных типов из соответствующих файлов,
     * создания карты и отправки её.
     *
     * @return объект класса Map (карта с территориями)
     */
    public Map loadAndPrepareData() {
        addBasicInfoToMap();
        addNumericalInfo();
        // addNumericProportionalInfo(NUM_PROPORTION_INFO);
        addTextInfo();
        return loadedMap;
    }

    /**
     * Метод загрузки основной информации, необходимой для создания объектов
     * класса Territory.
     */
    private void addBasicInfoToMap() {
        loadedMap = new MapCreator(ID_TYPE_CAPITAL).sendMap();
    }

    /**
     * Метод загрузки числовой информации, относящейся к конкретной территории.
     */
    private void addNumericalInfo() {
        loadedMap = new NumericInfoLoader(loadedMap, NUMERICAL_INFO).sendMap();
    }

    private void addNumericProportionalInfo(String numericalInfoForProportion) {
        // TODO Метод загрузки числовой информации, имеющей несколько заголовков, для создания графиков и диаграмм
    }

    /**
     * Метод загрузки текстовой информации, относящейся к конкретной территории.
     */
    private void addTextInfo() {
        loadedMap = new TextInfoLoader(loadedMap, TEXT_INFO).sendMap();
    }
}
