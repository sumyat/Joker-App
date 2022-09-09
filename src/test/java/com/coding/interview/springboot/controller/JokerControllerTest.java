package com.coding.interview.springboot.controller;

import com.coding.interview.springboot.model.Category;
import com.coding.interview.springboot.model.api.JokeResponse;
import com.coding.interview.springboot.model.domain.Joke;
import com.coding.interview.springboot.model.api.JokeRequest;
import com.coding.interview.springboot.service.DatabaseClient;
import com.coding.interview.springboot.service.JokerRestClient;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.net.SocketTimeoutException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
class JokerControllerTest {
    @Mock
    private DatabaseClient databaseClient;
    @Mock
    private JokerRestClient jokerRestClient;
    @InjectMocks
    private JokeController jokeController;

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class submit_a_joke {
        @Test
        void should_submit_a_joke() {
            Joke joke = getJoke();
            lenient().doNothing().when(databaseClient).createJokeResponse(joke);
            JokeRequest jokeRequest = getJokeRequest(joke);

            jokeController.submitAJoke(jokeRequest);

            verify(databaseClient).createJokeResponse(any(Joke.class));
        }
    }

    private JokeRequest getJokeRequest(Joke joke) {
        return JokeRequest.builder()
                .setup(joke.getSetup())
                .delivery(joke.getDelivery())
                .category(joke.getCategory())
                .build();
    }

    @Nested
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class retrieve_and_save_joke {

        @Test
        void should_get_a_joke_from_api_and_save_to_db() {
            JokeResponse jokeResponse = getTestJoke();
            Mono<JokeResponse> expected = Mono.just(jokeResponse);
            when(jokerRestClient.getJokeResponse()).thenReturn(expected);
            when(databaseClient.findById(jokeResponse.getId())).thenReturn(Optional.empty());
            doNothing().when(databaseClient).createJokeResponse(any(Joke.class));

            ResponseEntity<JokeResponse> actual = jokeController.retrieveAndSaveJoke();

            assertThat(actual.getStatusCodeValue()).isEqualTo(200);
            verify(databaseClient).createJokeResponse(any(Joke.class));
        }

        @Test
        void should_get_a_joke_from_api_and_do_not_save_to_db() {
            JokeResponse jokeResponse = getTestJoke();
            Mono<JokeResponse> expected = Mono.just(jokeResponse);
            when(jokerRestClient.getJokeResponse()).thenReturn(expected);
            when(databaseClient.findById(jokeResponse.getId())).thenReturn(Optional.of(getJoke()));

            ResponseEntity<JokeResponse> actual = jokeController.retrieveAndSaveJoke();

            assertThat(actual.getStatusCodeValue()).isEqualTo(200);
            verify(databaseClient, never()).createJokeResponse(any(Joke.class));
        }

        @Test
        void should_get_a_joke_from_db_when_api_is_having_error() {
            when(jokerRestClient.getJokeResponse())
                    .thenReturn(Mono.error(new SocketTimeoutException("Oh no, server is red!")));

            Joke joke = getJoke();
            when(databaseClient.findLastJoke()).thenReturn(Optional.of(joke));

            ResponseEntity<JokeResponse> actual = jokeController.retrieveAndSaveJoke();

            assertThat(actual.getStatusCodeValue()).isEqualTo(200);
            verify(databaseClient).findLastJoke();
        }
    }

    private JokeResponse getTestJoke() {
        return JokeResponse.builder()
                .id(1)
                .joke("testJoke")
                .category(Category.MISC)
                .build();
    }

    private Joke getJoke() {
        return Joke.builder()
                .setup("Why did the kid cross the playground?")
                .delivery("To get to the other slide.")
                .category(Category.MISC)
                .build();
    }
}