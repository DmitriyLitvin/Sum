import java.util.List;
import java.util.concurrent.Callable;

public class SumCallable implements Callable<Double> {
    private List<Double> integers;

    public SumCallable(List<Double> integers) {
        this.integers = integers;
    }

    @Override
    public Double call() throws Exception {

        return integers.stream().reduce(0.0, (x, y) -> x + y);
    }
}

