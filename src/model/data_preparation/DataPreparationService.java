package model.data_preparation;

import java.io.File;

import model.map.Map;

public class DataPreparationService {

    private Map loadedMap;
    private final File ID_TYPE_CAPITAL = new File("bd.csv");
    private final File NAMES;
    private final File NUMERICAL_INFO = new File("nd.csv");
    // private final File NUM_PROPORTION_INFO = new File("pd.csv");
    // private final File TEXT_INFO = new File("td.csv");

    public DataPreparationService(String language) {
        NAMES = new File(language + "names.txt");
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
        loadedMap = new NumericalInfoLoader(loadedMap, NUMERICAL_INFO).sendMap();
    }

    private void addNumericalProportionalInfo(File numericalInfoForProportion) {
        //
    }

    private void addTextInfo(File textInfo) {
        //
    }
}