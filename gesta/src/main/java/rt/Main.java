package rt;

import rt.model.Service;
import rt.model.territory.TerritoryType;
import rt.model.types.Operator;

public class Main {
    public static void main(String[] args) {

        Service service = new Service("rus");
        // System.out.println(service.findByLevel(Operator.EQUAL, 2));
        // System.out.println(service.chooseTerritory("RUS_CFO"));
        System.out.println(service.getListOfSubunitsOnID("RUS"));
        // System.out.println(service.getSortedList());
        // System.out.println(service.findByTerritoryType(TerritoryType.FEDERALDISTRICT));
    }
}