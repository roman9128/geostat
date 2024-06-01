
public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        
        service.addTerritory("Russia", 1, TerritoryType.Country, null, null, null, false);
        service.getList();

    }
}
