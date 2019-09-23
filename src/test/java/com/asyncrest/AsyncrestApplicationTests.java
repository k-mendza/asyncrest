package com.asyncrest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;

import static java.time.Duration.ofSeconds;

@SpringBootTest
class AsyncrestApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testFlux() {
		ConnectableFlux<Object> publish = Flux.create(fluxSink -> {
			while(true) {
				fluxSink.next(System.currentTimeMillis());
			}
		})
				.sample(ofSeconds(2))
				.publish();

		publish.subscribe();
	}

}
