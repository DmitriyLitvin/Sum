import java.util.List;

public class Main {
    public static void main(String[] args) {
        Creatable creatable = new CreateDouble();
        List<Double> doubles = creatable.createNumbers(10);
        System.out.println(new SumRecursiveTask(doubles).sum());
    }
}
