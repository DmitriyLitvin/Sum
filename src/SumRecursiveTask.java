import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumRecursiveTask extends RecursiveTask implements Summable<Double> {
    private List<Double> doubles;
    private int start;
    private int end;

    public SumRecursiveTask(List<Double> doubleList) {
        this(doubleList, 0, doubleList.size());
    }

    private SumRecursiveTask(List<Double> doubleList, int start, int end) {
        this.doubles = doubleList;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        int length = end - start;

        if (length < 10) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                sum += doubles.get(i);
            }

            return sum;
        }

        int split = length / 2;
        SumRecursiveTask left = new SumRecursiveTask(doubles, start, start + split);
        left.fork();
        SumRecursiveTask right = new SumRecursiveTask(doubles, start + split, end);

        return (Double) left.join() + right.compute();
    }

    @Override
    public Double sum() {
        ForkJoinPool pool = new ForkJoinPool(2);
        SumRecursiveTask sumRecursiveTask = new SumRecursiveTask(doubles);

        return (Double) pool.invoke(sumRecursiveTask);
    }
}
