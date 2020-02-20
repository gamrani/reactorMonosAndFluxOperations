import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ProductService {
    public List<Integer> getHistoricalPrices(String productId) {
        return new ArrayList<>();
    }

    public Stream<Integer> getDetails(int price) {
        return Arrays.asList(1,2,3).stream();
    }
}
