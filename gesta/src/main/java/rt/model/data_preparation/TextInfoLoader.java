package rt.model.data_preparation;

import rt.model.map.Map;

public class TextInfoLoader extends XLSXDataLoader {

    private Map map;
    private String[] dataNames;

    public TextInfoLoader(Map map, String path) {
        this.map = map;
        loadData(path, true);
    }

    public Map sendMap() {
        return map;
    }

    @Override
    protected void sendTitle(String[] title) {
        String[] titleString = new String[title.length];
        System.arraycopy(title, 0, titleString, 0, title.length);
        dataNames = titleString;
    }

    @Override
    protected void sendData(String[] data) {
        if (dataNames.length > data.length) {
            String[] dataSubstitute = new String[dataNames.length];
            System.arraycopy(data, 0, dataSubstitute, 0, data.length);
            data = dataSubstitute;
        }
        for (int i = 1; i < dataNames.length; i++) {
            if (data[i] != null) {
                map.getMapAsHashMap().get(data[0]).setTextInfo(dataNames[i], data[i]);
            } else {
                map.getMapAsHashMap().get(data[0]).setTextInfo(dataNames[i], "");
            }
        }
    }
}