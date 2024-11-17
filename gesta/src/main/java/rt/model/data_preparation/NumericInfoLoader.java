package rt.model.data_preparation;

import java.util.Arrays;

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
    protected void sendTitle(Object[] title) {
        String[] titleString = new String[title.length];
        for (int i = 0; i < title.length; i++) {
            titleString[i] = title[i].toString();
        }
        dataNames = titleString;
    }

    @Override
    protected void sendData(Object[] data) {
        if (dataNames.length > data.length) {
            Object[] dataSubstitute = new Object[dataNames.length];
            for (int i = 0; i < data.length; i++) {
                dataSubstitute[i] = data[i];
            }
            data = dataSubstitute;
        }
        for (int i = 1; i < dataNames.length; i++) {
            if (data[i] != null) {
                map.getMapAsHashMap().get(data[0]).setNumericalData(dataNames[i], ((Long) data[i]));
            } else {
                map.getMapAsHashMap().get(data[0]).setNumericalData(dataNames[i], 0);
            }
        }
    }
}