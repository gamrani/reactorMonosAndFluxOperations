import org.junit.Before;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import java.time.Duration;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class FluxTest {

    private String fluxResult;

    @Before
    public void setup(){
        fluxResult = "fluxResult";
    }

    private void setFluxResult(String fluxResult){
        this.fluxResult = fluxResult;
    }



    @Test
    public void simpleFlux(){
        Flux.just("A","B","C")
                .log()
                .subscribe(s -> {
                    if(s.equals("A")){
                        System.out.println(s);
                        this.setFluxResult(s);
                    }
                });

        assertEquals("A",fluxResult);
    }


    @Test
    public void simpleFlux_fromIterable(){
        Flux.fromIterable(Arrays.asList("Green","Red","Yellow"))
                .log()
                .subscribe();
    }

    @Test
    public void fluxRange(){
        Flux.range(10,10)
                .log()
                .subscribe();
    }

    @Test
    public void fluxIntervalStream() throws InterruptedException {
        Flux.interval(Duration.ofSeconds(1))
                .log()
                .take(2)
                .subscribe();
        Thread.sleep(5000);
    }

    @Test
    public void fluxRequest() throws InterruptedException {
        Flux.range(1,10)
                .log()
                .subscribe(null,
                        null,
                        null,
                        s->s.request(3));
    }
}
