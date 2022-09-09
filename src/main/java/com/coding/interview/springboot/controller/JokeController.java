package com.coding.interview.springboot.controller;

import com.coding.interview.springboot.model.api.JokeRequest;
import com.coding.interview.springboot.model.api.JokeResponse;
import com.coding.interview.springboot.model.domain.Joke;
import com.coding.interview.springboot.service.DatabaseClient;
import com.coding.interview.springboot.service.JokerRestClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class JokeController {
    private final JokerRestClient jokerRestClient;
    private final DatabaseClient databaseClient;

    @GetMapping("/retrieve-and-save-joke")
    public ResponseEntity<JokeResponse> retrieveAndSaveJoke() {
        try {
            //If JokerAPI is down, get from local db.
            JokeResponse jokeResponse = jokerRestClient.getJokeResponse().block();

            if (jokeResponse == null) {
                return ResponseEntity.notFound().build();
            }

            //If joke is not found by id, then save to local database.
            if(databaseClient.findById(jokeResponse.getId()).isEmpty()) {
                Joke joke = convertJokeResponseToJoke(jokeResponse);
                databaseClient.createJokeResponse(joke);
            }
            log.info("Successfully retrieved a joke from API!");
            return ResponseEntity.ok(jokeResponse);
        } catch (Exception exception) {
            //If JokeAPI is having an issue, then retrieve from local db.
            Optional<JokeResponse> jokeResponse = databaseClient.findLastJoke()
                    .map(joke -> JokeResponse.builder()
                            .setup(joke.getSetup())
                            .delivery(joke.getDelivery())
                            .category(joke.getCategory())
                            .joke(joke.getJoke())
                            .build());

            if (jokeResponse.isPresent()) {
                log.info("Successfully retrieved a joke from DB!");
                return ResponseEntity.ok(jokeResponse.get());
            }
            return ResponseEntity.notFound().build();
        }
    }

    private Joke convertJokeResponseToJoke(JokeResponse jokeResponse) {
        return Joke
                .builder()
                .setup(jokeResponse.getSetup())
                .delivery(jokeResponse.getDelivery())
                .category(jokeResponse.getCategory())
                .joke(jokeResponse.getJoke())
                .build();
    }

    @PostMapping("/submit-a-joke")
    public void submitAJoke(@Valid @RequestBody JokeRequest jokeRequest) {
        Joke joke = convertJokeRequestToJoke(jokeRequest);
        log.info("Successfully saved a joke to DB!");
        databaseClient.createJokeResponse(joke);
    }

    private Joke convertJokeRequestToJoke(JokeRequest jokeRequest) {
        return Joke
                .builder()
                .setup(jokeRequest.getSetup())
                .delivery(jokeRequest.getDelivery())
                .category(jokeRequest.getCategory())
                .joke(jokeRequest.getJoke())
                .build();
    }
}
