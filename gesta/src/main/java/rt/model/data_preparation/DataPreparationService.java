package rt.model.data_preparation;

import rt.model.localizator.CommonLocalizator;
import rt.model.map.Map;

public final class DataPreparationService {

    private Map loadedMap;
    private final CommonLocalizator localizator;
    private final String ID_TYPE_CAPITAL = "bd.xlsx";
    private final String NUMERICAL_INFO = "nd.xlsx";
    // private final String NUM_PROPORTION_INFO = "pd.csv";
    private final String TEXT_INFO = "td.xlsx";

    public DataPreparationService(CommonLocalizator localizator) {
        this.localizator = localizator;
        loadAndPrepareData();
    }

    public Map loadAndPrepareData() {
        addBasicInfoToMap();
        addNumericalInfo();
        // addNumericalProportionalInfo(NUM_PROPORTION_INFO);
        addTextInfo();
        return loadedMap;
    }

    private void addBasicInfoToMap() {
        loadedMap = new MapCreator(ID_TYPE_CAPITAL, localizator).sendMap();
    }

    private void addNumericalInfo() {
        loadedMap = new NumericInfoLoader(loadedMap, NUMERICAL_INFO).sendMap();
    }

    private void addNumericalProportionalInfo(String numericalInfoForProportion) {
        //
    }

    private void addTextInfo() {
        loadedMap = new TextInfoLoader(loadedMap, TEXT_INFO).sendMap();
    }
}