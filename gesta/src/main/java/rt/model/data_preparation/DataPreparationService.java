package rt.model.data_preparation;

import rt.model.map.Map;

public final class DataPreparationService {

    private Map loadedMap;
    private final String ID_TYPE_CAPITAL = "bd.xlsx";
    private final String NAMES;
    private final String NUMERICAL_INFO = "nd.xlsx";
    // private final String NUM_PROPORTION_INFO = "pd.csv";
    // private final String TEXT_INFO = "td.csv";

    public DataPreparationService(String language) {
        NAMES = language + "names.xlsx";
        loadAndPrepareData();
    }

    public Map loadAndPrepareData() {
        addBasicInfoToMap();
        addNumericalInfo();
        // addNumericalProportionalInfo(NUM_PROPORTION_INFO);
        // addTextInfo(TEXT_INFO);
        return loadedMap;
    }

    private void addBasicInfoToMap() {
        loadedMap = new MapCreator(ID_TYPE_CAPITAL, NAMES).sendMap();
    }

    private void addNumericalInfo() {
        loadedMap = new NumericInfoLoader(loadedMap, NUMERICAL_INFO).sendMap();
    }

    private void addNumericalProportionalInfo(String numericalInfoForProportion) {
        //
    }

    private void addTextInfo(String textInfo) {
        //
    }
}