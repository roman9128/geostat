import model.Service;
import model.territory.TerritoryType;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        
        service.addTerritory("RUS", 1, "Russia", TerritoryType.Country, false);
        System.out.println(service.getList());
        

    }
}
