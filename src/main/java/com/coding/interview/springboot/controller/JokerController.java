package com.coding.interview.springboot.controller;

import com.coding.interview.springboot.model.JokeResponse;
import com.coding.interview.springboot.service.JokerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class JokerController {
    private final JokerClient jokerClient;

    public JokerController(JokerClient jokerClient) {
        this.jokerClient = jokerClient;
    }

    @GetMapping("/get-a-joke")
    public Mono<JokeResponse> getAJoke() {
        return jokerClient.getJokerResponse();
    }
}
