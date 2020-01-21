import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class FluxAndMonoOperatorsTest {

    @Test
    public void map(){
        Flux.range(1,10)
                .log()
                .map(i -> i*10)
                .subscribe(System.out::println);
    }

    @Test
    public void flatMap(){
        Flux.range(1,10)
                .log()
                .flatMap(i -> Flux.range(i*10,2))
                .subscribe(System.out::println);
    }

}
