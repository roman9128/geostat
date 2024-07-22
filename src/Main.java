import model.Service;
import model.types.Operator;

public class Main {
    public static void main(String[] args) {

        Service service = new Service();
        System.out.println(service.findByParameter("population", Operator.less, 1000000));
    }
}