package com.coding.interview.springboot.model.api;

import com.coding.interview.springboot.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JokeResponse {
    @NotNull
    private Integer id;

    private String setup;

    private String delivery;

    private String joke;

    private Category category;
}
