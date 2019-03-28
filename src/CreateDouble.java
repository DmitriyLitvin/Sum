import java.util.ArrayList;
import java.util.List;

public class CreateDouble implements Creatable<Double> {

    @Override
    public List<Double> createNumbers(int size) {
        List<Double> doubles = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            doubles.add((double) i);
        }
        return doubles;
    }
}
