import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class FluxAndMonoOperatorsTest {

    @Test
    public void fluxMapFilterAndBuffer(){
        Flux.range(0,10)
                .log()
                .map(input -> input + 1)
                .filter(input -> input % 2 == 0)
                .buffer(2)
                .subscribe(System.out::println);

    }

    @Test
    public void fluxMapFilterAndBufferShouldComplete(){
        List<Integer> expectedFirstResult = Arrays.asList(2,4,6);
        List<Integer> expectedSecondResult = Arrays.asList(8,10);

        Flux<List<Integer>> fluxToTest= Flux.range(0,10)
                .map(input -> input + 1)
                .filter(input -> input % 2 == 0)
                .buffer(3);

        StepVerifier.create(fluxToTest)
                .expectNext(expectedFirstResult)
                .expectNext(expectedSecondResult)
                .expectComplete()
                .verify();

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
        Flux<Integer> notes = Flux.range(1,5) ;
        Flux<String> persons = Flux.fromIterable(Arrays.asList("Octo1","Octo2","Octo3"));
        Flux.concat( persons,notes)
                .subscribe(System.out::println);
    }

    @Test
    public void mergeFluxes() throws InterruptedException {
        Flux<Integer> notes = Flux.range(1,5)
                .delayElements(Duration.ofMillis(300));
        Flux<String> persons = Flux.fromIterable(Arrays.asList("Octo1","Octo2","Octo3"))
                .delayElements(Duration.ofMillis(600));

        Flux.merge(notes, persons)
                .subscribe(System.out::println);

        Thread.sleep(4000);

    }

    @Test
    public void zipFluxes (){
        Flux<Integer> notes = Flux.range(1,10);
        Flux<String> persons = Flux.fromIterable(Arrays.asList("Octo1","Octo2","Octo3","Octo4"));

        Flux.zip(notes,persons,
                (item1, item2) -> item2 + ":" +item1)
                .subscribe(System.out::println);
    }

}
