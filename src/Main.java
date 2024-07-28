import model.Service;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        // System.out.println(service.getSortedList());
        service.createTerritorySet("newrandomset");
        service.addTerritoryToSet("newrandomset", "RUS_CFO_BRJ");
        service.addTerritoryToSet("newrandomset", "RUS_CFO_BEL");
        System.out.println(service.getSetByName("newrandomset"));
    }
}