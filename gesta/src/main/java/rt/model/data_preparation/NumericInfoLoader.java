package rt.model.data_preparation;

import rt.model.map.Map;

public class NumericInfoLoader extends XLSXDataLoader {

    private Map map;
    private String[] dataNames;

    public NumericInfoLoader(Map map, String path) {
        this.map = map;
        loadData(path, true);
    }

    public Map sendMap() {
        return map;
    }

    // private void setUserDataNames(String[] text) {
    // String[] dataNamesToShow = new String[text.length - 1];
    // for (int i = 1; i < text.length; i++) {
    // dataNamesToShow[i - 1] = text[i];
    // }
    // map.setUserDataNames(dataNamesToShow);
    // }
    @Override
    protected void sendTitle(String[] title) {
        String[] titleString = new String[title.length];
        System.arraycopy(title, 0, titleString, 0, title.length);
        dataNames = titleString;
    }

    @Override
    protected void sendData(String[] data) { // extract to method
        if (dataNames.length > data.length) {
            String[] dataSubstitute = new String[dataNames.length];
            System.arraycopy(data, 0, dataSubstitute, 0, data.length);
            data = dataSubstitute;
        }
        for (int i = 1; i < dataNames.length; i++) {
            if (data[i] != null) {
                map.getMapAsHashMap().get(data[0]).setNumericData(dataNames[i], (Long.parseLong(data[i])));
            } else {
                map.getMapAsHashMap().get(data[0]).setNumericData(dataNames[i], 0);
            }
        }
    }
}
