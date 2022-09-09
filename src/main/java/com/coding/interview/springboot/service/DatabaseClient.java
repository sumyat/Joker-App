package com.coding.interview.springboot.service;

import com.coding.interview.springboot.model.domain.Joke;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DatabaseClient {
    private JokeRepository jokeRepository;
    public void createJokeResponse(Joke joke) {
        jokeRepository.save(joke);
    }

    public Optional<Joke> findById(Integer id) {
        return jokeRepository.findById(id);
    }
    public Optional<Joke> findLastJoke() {
        return jokeRepository.findLastJoke();
    }
}
