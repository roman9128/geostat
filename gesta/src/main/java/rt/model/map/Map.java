package rt.model.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import rt.model.enums.Operator;
import rt.model.enums.TerritoryType;
import rt.model.territory.Territory;
import rt.model.territory.comparators.ComparatorByName;
import rt.model.territory_set.TerritorySet;

/**
 * Класс, содержащий информацию о территориях и
 * пользовательских подборках территорий.
 */
public class Map {

    private HashMap<String, Territory> map; // хранилище для территорий
    private HashMap<String, TerritorySet> sets; // хранилище для пользовательских подборок территорий
    private String[] userNumericDataNames; // заголовки пользовательских числовых данных

    public Map() {
        map = new HashMap<>();
    }

    /**
     * Метод получения пользовательских подборок территорий.
     *
     * @return HashMap<String, TerritorySet>
     */
    public HashMap<String, TerritorySet> getSetsAsHashMap() {
        return sets;
    }

    /**
     * Метод добавления созданной подборки на карту.
     *
     * @param setName наименование подборки, придумываемое пользователем
     * @param terrSet сама подборка территорий
     */
    public void addSet(String setName, TerritorySet terrSet) {
        if (sets == null) {
            sets = new HashMap<>();
        }
        this.sets.put(setName, terrSet);
    }

    /**
     * Метод удаления подборки.
     *
     * @param setName имя подборки, ключ соответствующего
     * HashMap<String, TerritorySet>
     */
    public void removeSet(String setName) {
        sets.remove(setName);
    }

    /**
     * Метод для переименования подборки. Переименование происходит путём
     * создания нового объекта класса TerritorySet.
     *
     * @param previousSetName предыдущее наименование
     * @param newSetName новое наименование
     */
    public void renameSet(String previousSetName, String newSetName) {
        TerritorySet newSet = new TerritorySet(newSetName);
        newSet.setTerritories(getSetsAsHashMap().get(previousSetName).getTerritories());
        newSet.setNumericData(getSetsAsHashMap().get(previousSetName).getNumericData());
        addSet(newSetName, newSet);
        removeSet(previousSetName);
    }

    /**
     * Метод получения заголовков пользовательских числовых данных.
     *
     * @return массив строк
     */
    public String[] getUserNumericDataNames() {
        return userNumericDataNames;
    }

    /**
     * Метод установления заголовков пользовательских числовых данных.
     *
     * @param userNumericDataNames массив строк, содержащий заголовки
     * пользовательских числовых данных
     */
    public void setUserNumericDataNames(String[] userNumericDataNames) {
        this.userNumericDataNames = userNumericDataNames;
    }

    /**
     * Метод добавления территории на карту.
     *
     * @param id уникальный идентификатор территории
     * @param territory территория
     */
    public void addToMap(String id, Territory territory) {
        map.put(id, territory);
    }

    /**
     * Метод получения карты с территориями
     *
     * @return HashMap<String, Territory>
     */
    public HashMap<String, Territory> getMapAsHashMap() {
        return map;
    }

    /**
     * Метод заполнения созданной карты территориями из загрузчика данных.
     *
     * @param map подготовленная карта
     */
    public void setMap(HashMap<String, Territory> map) {
        this.map.putAll(map);
    }

    /**
     * Метод получения территории по её id.
     *
     * @param id уникальный идентификатор территории
     * @return объект класса Territory
     */
    public Territory getTerritoryOnID(String id) {
        return map.get(id);
    }

    /**
     * Метод получения id территории.
     *
     * @param territory территория, чей id необходимо определить
     * @return String id
     */
    public String getTerritoryID(Territory territory) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().equals(territory))
                .map(entry -> entry.getKey())
                .findFirst()
                .orElse(null);
    }

    /**
     * Поиск территорий по наименованию.
     *
     * @param name наименование
     * @return список территорий (пустой, если ничего не найдено)
     */
    public List<Territory> findByName(String name) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().getName().contains(name))
                .map(entry -> entry.getValue())
                .toList();
    }

    /**
     * Поиск территорий по типу.
     *
     * @param type тип территории из числа указанных в соответствующем enum
     * @return список территорий (пустой, если ничего не найдено)
     */
    public List<Territory> findByTerritoryType(TerritoryType type) {
        return map.entrySet().stream()
                .filter(entry -> entry.getValue().getType().equals(type))
                .map(entry -> entry.getValue())
                .toList();
    }

    /**
     * Поиск территорий по уровню с применением условий больше, меньше или
     * равно.
     *
     * @param operator условие больше, меньше или равно
     * @param number уровень территории
     * @return список территорий (пустой, если ничего не найдено)
     */
    public List<Territory> findByLevel(Operator operator, int number) {
        switch (operator) {
            case EQUAL -> {
                return map.entrySet().stream()
                        .filter(entry -> entry.getValue().getLevel() == number)
                        .map(entry -> entry.getValue())
                        .sorted(new ComparatorByName())
                        .toList();
            }
            case LESS -> {
                return map.entrySet().stream()
                        .filter(entry -> entry.getValue().getLevel() < number)
                        .map(entry -> entry.getValue())
                        .sorted(new ComparatorByName())
                        .toList();
            }
            case MORE -> {
                return map.entrySet().stream()
                        .filter(entry -> entry.getValue().getLevel() > number)
                        .map(entry -> entry.getValue())
                        .sorted(new ComparatorByName())
                        .toList();
            }
            default -> {
                return new ArrayList<>();
            }
        }
    }

    /**
     * Поиск территорий по каким-либо числовым данным с применением условий
     * больше, меньше или равно.
     *
     * @param dataName наименование искомых данных, должно соответствовать
     * заголовкам соответствующей таблицы
     * @param operator условие больше, меньше или равно
     * @param number числовое значение
     * @return список территорий (пустой, если ничего не найдено)
     */
    public List<Territory> findByParameter(String dataName, Operator operator, long number) {
        if (Arrays.asList(userNumericDataNames).contains(dataName)) {
            switch (operator) {
                case MORE -> {
                    return map.entrySet().stream()
                            .filter(entry -> entry.getValue().getNumericData().get(dataName) > number)
                            .map(entry -> entry.getValue())
                            .sorted(new ComparatorByName())
                            .toList();
                }
                case LESS -> {
                    return map.entrySet().stream()
                            .filter(entry -> entry.getValue().getNumericData().get(dataName) < number)
                            .map(entry -> entry.getValue())
                            .sorted(new ComparatorByName())
                            .toList();
                }
                case EQUAL -> {
                    return map.entrySet().stream()
                            .filter(entry -> entry.getValue().getNumericData().get(dataName) == number)
                            .map(entry -> entry.getValue())
                            .sorted(new ComparatorByName())
                            .toList();
                }
                default -> {
                    return new ArrayList<>();
                }
            }
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Поиск территорий по каким-либо числовым данным в пределах заданного
     * пользователем диапазона.
     *
     * @param dataName наименование искомых данных, должно соответствовать
     * заголовкам соответствующей таблицы
     * @param number1 начальное число диапазона
     * @param number2 конечное число диапазона
     * @return список территорий (пустой, если ничего не найдено)
     */
    public List<Territory> findByParameterWithinInterval(String dataName, long number1, long number2) {
        if (Arrays.asList(userNumericDataNames).contains(dataName)) {
            long smallerNumber; // небольшая проверка на тот случай, если пользователь начнёт диапазон большим числом
            long largerNumber;
            if (number1 > number2) {
                largerNumber = number1;
                smallerNumber = number2;
            } else {
                smallerNumber = number1;
                largerNumber = number2;
            }
            return map.entrySet().stream()
                    .filter(entry
                            -> smallerNumber <= entry.getValue().getNumericData().get(dataName) && entry.getValue().getNumericData().get(dataName) <= largerNumber)
                    .map(entry -> entry.getValue())
                    .sorted(new ComparatorByName())
                    .toList();
        } else {
            return new ArrayList<>();
        }
    }
}
