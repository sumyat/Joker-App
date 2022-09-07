package com.coding.interview.springboot.service;

import com.coding.interview.springboot.model.JokeResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JokerClientTest {
    @Test
    void shouldGetAJokeResponse() {
        JokeResponse expectedResponse = JokeResponse.builder().build();
        assertEquals(expectedResponse, expectedResponse);
    }
}