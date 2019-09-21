package com.asyncrest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import static java.time.Duration.ofSeconds;

@Component
public class Runner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
            while(true) {
                fluxSink.next(System.currentTimeMillis());
            }
        })
                .sample(ofSeconds(2))
                .publish();

        publish.subscribe(System.out::println);
    }
}
