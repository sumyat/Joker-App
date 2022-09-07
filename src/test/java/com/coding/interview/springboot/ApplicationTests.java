package com.coding.interview.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
		Mono<String> mono = Mono.just("String");
		
	}

}
