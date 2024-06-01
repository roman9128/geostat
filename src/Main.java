
public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        
        service.addTerritory("Верхличи", 5, TerritoryType.VillageSettlement, null, null, null, null, false);
        service.getList();

    }
}
