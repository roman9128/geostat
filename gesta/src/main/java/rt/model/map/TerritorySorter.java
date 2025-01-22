package rt.model.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import rt.model.territory.Territory;
import rt.model.territory.comparators.ComparatorByLevel;
import rt.model.territory.comparators.ComparatorByName;

/**
 * Класс для сортировки объектов класса Territory по имени и с учётом уровня с
 * занесением их в список.
 */
public class TerritorySorter {

    /**
     * Список отсортированных территорий.
     */
    private List<Territory> sortedTerritories;

    public TerritorySorter() {
    }

    /**
     * Метод, принимающий HashMap с объектами класса Territory и возвращающий
     * отсортированный по имени список этих объектов.
     *
     * @param territoriesToSort HashMap
     * @return List<Territory>
     */
    public List<Territory> sortTerritories(HashMap<String, Territory> territoriesToSort) {
        sortedTerritories = new ArrayList<>();
        mapToList(territoriesToSort);
        sortByName();
        return sortedTerritories;
    }

    /**
     * Сортировка по имени.
     */
    private void sortByName() {
        sortedTerritories.sort(new ComparatorByName());
    }

    /**
     * Преобразование HashMap<String, Territory> в ArrayList<Territory>.
     *
     * @param territoriesToSort HashMap<String, Territory>, который нужно
     * отсортировать
     */
    private void mapToList(HashMap<String, Territory> territoriesToSort) {
        List<Territory> tempList = new ArrayList<>();
        for (HashMap.Entry<String, Territory> entry : territoriesToSort.entrySet()) {
            tempList.add(entry.getValue());
        }
        tempList.sort(new ComparatorByLevel()); // сортировка по уровню, самый высокий уровень в начале списка
        for (Territory territory : tempList) {
            if (territory.getLevel() == tempList.get(0).getLevel()) { // в список добавляются только территории самого верхнего уровня
                sortedTerritories.add(territory);
            }
        }
    }
}
