import model.Service;
import model.types.Operator;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        System.out.println(service.findByLevel(Operator.equal, 1));
    }
}