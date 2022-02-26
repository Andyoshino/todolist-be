package com.practicereactor.monofluxtesting;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class MonoFluxTest {

    @Test
    public void fluxTest() {
        Flux<String> stringFlux = Flux.just("Johny", "Andreas", "Alice")
                //.concatWith(Flux.error(new RuntimeException("Error! :(")))
                .concatWith(Flux.just("Budi", "Cania"));
        //.log();

        stringFlux
                .subscribe(System.out::println,
                        e -> System.err.println(e),
                        () -> System.out.println("Sip."));
    }

    @Test
    public void fluxTest_withExpectation() {
        Flux<String> stringFlux = Flux.just("A", "B", "C", "D").log();

        StepVerifier.create(stringFlux)
                .expectNext("A")
                .expectNext("B")
                .expectNext("C")
                .expectNext("D")
                .verifyComplete();
    }

    @Test
    public void monoTest() {
        Mono<String> stringMono = Mono.just("A")
                .log();

        stringMono.subscribe(System.out::println);
    }

    @Test
    public void testFlatMap() {
        Mono<String> abc = Mono.just("Sebuah String");
    }


}
