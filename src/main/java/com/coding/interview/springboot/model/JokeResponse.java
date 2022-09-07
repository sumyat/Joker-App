package com.coding.interview.springboot.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JokeResponse {
    private String error;

    private String category;

    private String setup;

    private String delivery;

    private String id;
}
