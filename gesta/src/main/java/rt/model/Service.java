package rt.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import rt.model.data_preparation.DataPreparationService;
import rt.model.enums.Operator;
import rt.model.enums.TerritoryType;
import rt.model.localizator.CommonLocalizator;
import rt.model.localizator.LocalizationLoader;
import rt.model.map.Map;
import rt.model.map.TerritorySorter;
import rt.model.territory.Territory;
import rt.model.territory_set.TerritorySet;

/**
 * Сервисный класс, организующий работу всех остальных классов модели,
 * содержащий методы для работы с программой.
 */
public class Service {

    /**
     * Итоговая загруженная карта.
     */
    private final Map map;

    public Service() {
        chooseLanguage();
        new LocalizationLoader();
        map = new DataPreparationService().loadAndPrepareData();
    }

    /**
     * Создание подборки территорий.
     *
     * @param setName наименование подборки
     */
    public void createTerritorySet(String setName) {
        map.addSet(setName, new TerritorySet(setName));
    }

    /**
     * Добавление территории в подборку.
     *
     * @param setName наименование подборки
     * @param id идентификатор территории
     */
    public void addTerritoryToSet(String setName, String id) {
        if (map.getMapAsHashMap().containsKey(id) && map.getSetsAsHashMap().containsKey(setName)) {
            map.getSetsAsHashMap().get(setName).addToSet(id, map.getTerritoryOnID(id));
        }
    }

    /**
     * Удаление территории из подборки.
     *
     * @param setName наименование подборки
     * @param id идентификатор удаляемой территории
     */
    public void removeTerritoryFromSet(String setName, String id) {
        if (map.getMapAsHashMap().containsKey(id) && map.getSetsAsHashMap().containsKey(setName)) {
            map.getSetsAsHashMap().get(setName).removeFromSet(id);
        }
    }

    /**
     * Получение наименований подборок территорий.
     *
     * @return ArrayList<String>
     */
    public ArrayList<String> getTerritorySetsNames() {
        ArrayList<String> result = new ArrayList<>();
        for (HashMap.Entry<String, TerritorySet> entry : map.getSetsAsHashMap().entrySet()) {
            result.add(entry.getKey());
        }
        result.sort(Comparator.naturalOrder());
        return result;
    }

    /**
     * Получение всех подборок территорий.
     *
     * @return HashMap<String, TerritorySet>
     */
    public HashMap<String, TerritorySet> getAllSets() {
        return map.getSetsAsHashMap();
    }

    /**
     * Получение содержательной информации изо всех подборок.
     *
     * @return String
     */
    public String getAllSetsInfo() {
        StringBuilder builder = new StringBuilder();
        if (getAllSets() != null) {
            for (Object set : getAllSets().entrySet().stream().map(entry -> entry.getValue()).toArray()) {
                builder.append(set);
            }
        }
        return builder.toString();
    }

    /**
     * Получение подборки по наименованию.
     *
     * @param setName наименование подборки
     * @return TerritorySet
     */
    public TerritorySet getSetByName(String setName) {
        return map.getSetsAsHashMap().get(setName);
    }

    /**
     * Переименование подборки.
     *
     * @param previousSetName старое наименование
     * @param newSetName новое наименование
     */
    public void renameSet(String previousSetName, String newSetName) {
        map.renameSet(previousSetName, newSetName);
    }

    /**
     * Удаление подборки по наименованию.
     *
     * @param setName наименование подборки
     */
    public void removeSet(String setName) {
        map.removeSet(setName);
    }

    /**
     * Поиск территории по наименованию.
     *
     * @param name наименование территории
     * @return Полная информация о территории кроме её структурных единиц в
     * строковом выражении
     */
    public String findByName(String name) {
        return printResult(map.findByName(name));
    }

    /**
     * Поиск территории по типу.
     *
     * @param type тип территории
     * @return Полная информация о территории кроме её структурных единиц в
     * строковом выражении
     */
    public String findByTerritoryType(TerritoryType type) {
        return printResult(map.findByTerritoryType(type));
    }

    /**
     * Поиск территорий по уровню с применением условий больше, меньше или
     * равно.
     *
     * @param operator условие больше, меньше или равно
     * @param number уровень территории
     * @return список территорий (пустой, если ничего не найдено) в строковом
     * выражении
     */
    public String findByLevel(Operator operator, int number) {
        return printResult(map.findByLevel(operator, number));
    }

    /**
     * Поиск территорий по каким-либо числовым данным с применением условий
     * больше, меньше или равно.
     */
    public String findByParameter(String dataName, Operator operator, long number) {
        return printResult(map.findByParameter(dataName, operator, number));
    }

    /**
     * Поиск территорий по каким-либо числовым данным в пределах заданного
     * пользователем диапазона.
     */
    public String findByParameterWithinInterval(String dataName, long number1, long number2) {
        return printResult(map.findByParameterWithinInterval(dataName, number1, number2));
    }

    /**
     * Выбрать территорию по id.
     */
    public Territory chooseTerritory(String id) {
        return map.getTerritoryOnID(id);
    }

    /**
     * Получение списка всех территорий, имеющих самый высокий уровень на карте.
     */
    public String getListOfTerritoriesWithTheHighestLevelOnMap() {
        StringBuilder builder = new StringBuilder();
        builder.append(getListTitle("LIST"));
        List<Territory> listToShow = new TerritorySorter().sortTerritories(map.getMapAsHashMap());
        for (Territory territory : listToShow) {
            builder.append(System.lineSeparator())
                    .append(territory.getName())
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * Получение списка структурных единиц по id территории в зависимости от их
     * уровня. Например, РФ содержит федеральные округа, которые включают в себя
     * субъекты РФ (области и т.д.), которые делятся на муниципальные
     * образования. Таким образом, организационная структура какой-либо
     * территории может подразумевать несколько уровней "подчинённых"
     * территорий. Для учёта этой многослойности используется переменная
     * requiredDepth.
     *
     * @param id id территории
     * @param requiredDepth требуемая глубина погружения в уровни структурных
     * единиц
     * @return список структурных единиц с отступами для каждого уровня
     */
    public String getListOfSubunitsByTheirLevelOnID(String id, int requiredDepth) {
        StringBuilder builder = new StringBuilder();
        Territory territory = map.getTerritoryOnID(id);
        builder.append(getListTitle("LIST"))
                .append(territory.getName())
                .append(System.lineSeparator())
                .append(getListOfSubunits(territory, 1, requiredDepth));
        return builder.toString();
    }

    /**
     * Подготовка списка структурных единиц территории.
     */
    private String getListOfSubunits(Territory territory, int depth, int requiredDepth) {
        if (territory.getSubunits() == null) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        String tab = "\t";
        List<Territory> subunits = new TerritorySorter().sortTerritories(territory.getSubunits());
        for (Territory subunit : subunits) {
            builder.append(tab.repeat(depth))
                    .append(subunit.getName())
                    .append(System.lineSeparator());
            if (requiredDepth > 0) {
                builder.append(getListOfSubunits(subunit, depth + 1, requiredDepth - 1));
            }
        }
        return builder.toString();
    }

    /**
     * Формирование строки с информацией о территориях.
     */
    private String printResult(List<Territory> result) {
        StringBuilder builder = new StringBuilder();
        builder.append(getListTitle("RESULT"));
        if (result.isEmpty()) {
            builder.append(CommonLocalizator.getLocalizedText("NO_RESULT"));
        }
        for (Territory territory : result) {
            builder.append(territory);
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    /**
     * Подготовка локализованных заголовков списка.
     *
     * @param title идентификатор заголовка
     * @return заголовок
     */
    private String getListTitle(String title) {
        return CommonLocalizator.getLocalizedText(title) + ": " + System.lineSeparator();
    }

    /**
     * Метод выбора одного из доступных языков. Временное решение.
     */
    private void chooseLanguage() {
        File language = new File("language.txt");
        String previousLanguage = "";
        try (Scanner fileScanner = new Scanner(language)) {
            previousLanguage = fileScanner.nextLine();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        if (previousLanguage.isEmpty() || !language.exists()) {
            Scanner consoleScanner = new Scanner(System.in);
            ArrayList<String> languages = new ArrayList<>();
            languages.add("rus");
            languages.add("eng");
            System.out.println("Choose one of available languages / Выберите один из доступных языков");
            for (String string : languages) {
                System.out.println(string);
            }
            String choice = consoleScanner.nextLine();
            try (FileWriter fileWriter = new FileWriter(language, false)) {
                fileWriter.write(choice);
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            } finally {
                consoleScanner.close();
            }
        }
    }
}
