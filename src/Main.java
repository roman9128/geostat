import model.Service;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        service.createTerritorySet("firstset");
        service.createTerritorySet("secondset");
        service.addTerritoryToSet("firstset", "RUS_CFO_BEL");
        service.addTerritoryToSet("firstset", "RUS_CFO_BRJ");
        service.addTerritoryToSet("secondset", "RUS_CFO_VOR");
        service.addTerritoryToSet("secondset", "RUS_CFO_TUL");
        System.out.println(service.getSetByName("firstset"));
    }
}