package rt;

import rt.model.Service;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        // System.out.println(service.findByLevel(Operator.MORE, 2));
        // System.out.println(service.chooseTerritory("RUS_SFO"));
        // System.out.println(service.getListOfSubunitsByTheirLevelOnID("RUS", 1));
        // System.out.println(service.findByTerritoryType(TerritoryType.FEDERALCITY));
        // System.out.println(service.findByName("Россия"));
        // System.out.println(service.findByParameter("Население", Operator.LESS, 500000));
        // System.out.println(service.findByParameterWithinInterval("Площадь", 100000, 200000));
        // service.createTerritorySet("Черноземье");
        // service.addTerritoryToSet("Черноземье", "RUS_CFO_ORL");
        // service.addTerritoryToSet("Черноземье", "RUS_CFO_VOR");
        // service.addTerritoryToSet("Черноземье", "RUS_CFO_BEL");
        // System.out.println(service.getAllSetsInfo());
        // service.removeTerritoryFromSet("Черноземье", "RUS_CFO_ORL");
        // System.out.println(service.getAllSetsInfo());
    }
}

// тест программы через service в Main в связи с отсутствием GUI и нецелесообразностью создания консольного вида.