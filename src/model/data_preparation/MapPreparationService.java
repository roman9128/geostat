package model.data_preparation;

import java.io.File;

import model.map.Map;

public class MapPreparationService {

    private Map loadedMap;
    private final File ID_TYPE_CAPITAL = new File("bd.csv");
    // private final File NUMERICAL_INFO = new File("nd.csv");
    // private final File NUM_PROPORTION_INFO = new File("pd.csv");
    // private final File TEXT_INFO = new File("td.csv");

    public MapPreparationService() {
        loadAndPrepareData();
    }

    public Map loadAndPrepareData() {
        loadIdTypeCapitalAndOrganizeMap();
        // loadNumericalInfo(NUMERICAL_INFO);
        // loadNumericalProportionalInfo(NUM_PROPORTION_INFO);
        // loadTextInfo(TEXT_INFO);
        return loadedMap;
    }

    private void loadIdTypeCapitalAndOrganizeMap() {
        loadedMap = new MapCreator(ID_TYPE_CAPITAL).sendMap();
    }

    private void loadNumericalInfo(File numericalInfo) {
        //
    }

    private void loadNumericalProportionalInfo(File numericalInfoForProportion) {
        //
    }

    private void loadTextInfo(File textInfo) {
        //
    }
}