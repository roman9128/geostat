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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    protected void sendData(String[] data) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
