import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Arrays;

public class FluxAndMonoOperatorsTest {

    @Test
    public void mapDataSynchronously(){
        Flux.range(1,10)
                .log()
                .map(i -> i*10)
                .subscribe(System.out::println);
    }

    @Test
    public void mapDataAsynchronously(){
        Flux.range(1,10)
                .log()
                .flatMap(i -> Flux.range(i*10,2))
                .subscribe(System.out::println);
    }

    @Test
    public void convertMonoIntoAFlux(){
        Mono.just(4)
                .log()
                .flatMapMany(i -> Flux.range(1,i))
                .subscribe(System.out::println);
    }

    @Test
    public void concatFluxes() throws InterruptedException {
        Flux<Integer> notes = Flux.range(1,10)
                .delayElements(Duration.ofMillis(200));
        Flux<String> persons = Flux.fromIterable(Arrays.asList("Youssef","Amine","Adil"))
                .delayElements(Duration.ofMillis(400));

        Flux.concat(notes, persons)
                .subscribe(System.out::println);

        Thread.sleep(4000);

    }

    @Test
    public void mergeFluxes() throws InterruptedException {
        Flux<Integer> notes = Flux.range(1,10)
                .delayElements(Duration.ofMillis(200));
        Flux<String> persons = Flux.fromIterable(Arrays.asList("Zack","Youssef","Amine","Adil"))
                .delayElements(Duration.ofMillis(400));

        Flux.merge(notes, persons)
                .subscribe(System.out::println);

        Thread.sleep(4000);

    }

    @Test
    public void zipFluxes (){
        Flux<Integer> notes = Flux.range(1,10);
        Flux<String> persons = Flux.fromIterable(Arrays.asList("Zack","Youssef","Amine","Adil"));

        Flux.zip(notes,persons,
                (item1, item2) -> item2 + ":" +item1)
                .subscribe(System.out::println);
    }

}
