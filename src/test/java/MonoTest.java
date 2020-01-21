import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

public class MonoTest {

    @Test
    public void simpleMono(){
        Mono.just("123L")
                .log()
                .subscribe();
    }

    @Test
    public void monoWithConsumer(){
        Mono.just("123L")
                .log()
                .subscribe(System.out::println);
    }

    @Test
    public void monoWithDoOn(){
        Mono.just("A")
                .log()
                .doOnSubscribe(subs -> System.out.println("Subscribed :"+ subs))
                .doOnRequest(request -> System.out.println("Request :" + request))
                .doOnSuccess(complete -> System.out.println("Complete : "+complete))
                .subscribe(System.out::println);
    }

    @Test
    public void monoWithError(){
        Mono.error(new RuntimeException())
                .log()
                .subscribe(System.out::println,
                        error -> System.out.println("erorr :"+ error.getMessage()),
                        System.out::println);
    }

    @Test
    public void monoWithError_cachingException(){
        Mono.error(new RuntimeException())
                .onErrorResume(e -> {
                    System.out.println("Caucht :"+e);
                    return Mono.just("Default");
                })
                .log()
                .subscribe(System.out::println);
    }
}
