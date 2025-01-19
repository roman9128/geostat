package rt.model.data_preparation;

import rt.model.map.Map;
/**
 * Класс для загрузки числовой информации в объекты класса Territory
 */
public class NumericInfoLoader extends XLSXDataLoader {

    private Map map;
    private String[] dataNames;

    /**
     * Конструктор класса
     * @param map объект класса Map (карта), содержащий в себе территории, информацию о которых нужно загрузить
     * @param path путь к файлу с числовой информацией
     */
    public NumericInfoLoader(Map map, String path) {
        this.map = map;
        loadData(path, true);
    }

    public Map sendMap() {
        map.setUserNumericDataNames(dataNames);
        return map;
    }

    @Override
    protected void setTitle(String[] title) {
        dataNames = title;
    }
/*
 * Во избежание ошибок, связанных с неполным заполнением ячеек таблицы XLSX-файла,
 * в расчёт принимаются только данные из столбцов, имеющих заголовки, все иные данные не учитываются.
 * Если длина массива с данными меньше длины массива с заголовками, то создаётся новый массив с данными длиной,
 * соответствующей длине массива с заголовками.
 */
    @Override
    protected void setData(String[] data) {
        if (dataNames.length > data.length) {
            String[] dataSubstitute = new String[dataNames.length];
            System.arraycopy(data, 0, dataSubstitute, 0, data.length);
            data = dataSubstitute;
        }
        for (int i = 1; i < dataNames.length; i++) {
            if (data[i] != null) {
                try { // попытка распарсить строку. в случае неудачи сохраняется ноль (не null)
                    map.getMapAsHashMap().get(data[0]).setNumericData(dataNames[i], (Long.parseLong(data[i])));
                } catch (NumberFormatException e) {
                    map.getMapAsHashMap().get(data[0]).setNumericData(dataNames[i], 0);
                }
            } else {
                map.getMapAsHashMap().get(data[0]).setNumericData(dataNames[i], 0);
            }
        }
    }
}
