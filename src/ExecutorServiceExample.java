import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceExample implements Summable<Double> {
    private List<Double>doubles;

    public ExecutorServiceExample(List<Double> doubles) {
        this.doubles = doubles;
    }

    public Double sum() {
        double sum = 0;
        int leftLimitation;
        int rightLimitation = 0;
        int size = doubles.size();
        int divider = size / 10;

        if (divider == 0) {
            divider = 1;
        }

        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<Double>> futureArrayList = new ArrayList<>();

        while (rightLimitation != size) {
            leftLimitation = rightLimitation;
            rightLimitation += divider;

            if (rightLimitation >= size) {
                rightLimitation = size;
            }

            List<Double> list = doubles.subList(leftLimitation, rightLimitation);
            Future<Double> doubleFuture = executor.submit(new SumCallable(list));
            futureArrayList.add(doubleFuture);
        }

        try {
            for (Future future : futureArrayList) {
                sum += (double) future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        return sum;
    }
}
