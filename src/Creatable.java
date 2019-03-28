import java.util.List;

public interface Creatable<T extends Number> {
    List<T> createNumbers(int size);
}
