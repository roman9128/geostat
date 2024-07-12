import model.Service;
import model.types.DataType;
import model.types.Operator;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        System.out.println(service.findByParameter(DataType.level, Operator.equal, 2));
    }
}