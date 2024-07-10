import model.Service;
import model.territory.TerritoryType;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        System.out.println(service.findByParameter(TerritoryType.AutonomousOkrug));
    }
}