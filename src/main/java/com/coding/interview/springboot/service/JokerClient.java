package com.coding.interview.springboot.service;

import com.coding.interview.springboot.model.JokeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JokerClient {
    private final WebClient webClient;

    private static final String BASE_URL = "https://v2.jokeapi.dev";
    private static final String SINGLE_JOKE_ENDPOINT = "/joke/Any?blacklistFlags=nsfw,religious,political,racist,sexist,explicit";

    public JokerClient(WebClient.Builder builder) {
        this.webClient = builder.baseUrl(BASE_URL).build();
    }
    public Mono<JokeResponse> getJokerResponse() {
        return this.webClient
                .get()
                .uri(SINGLE_JOKE_ENDPOINT)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(JokeResponse.class)
                .doOnError(__ -> log.error("Error processing at the response"));
    }
}
