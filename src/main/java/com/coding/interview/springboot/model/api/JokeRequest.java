package com.coding.interview.springboot.model.api;

import com.coding.interview.springboot.model.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Builder
public class JokeRequest {
    @Pattern(regexp = "[\\w*\\s*]*", message = "Please enter only valid setup!")
    private String setup;

    @Pattern(regexp = "[\\w*\\s*]*", message = "Please enter only valid delivery!")
    private String delivery;

    @Pattern(regexp = "[\\w*\\s*]*", message = "Please enter only valid joke!")
    private String joke;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "The category is required.")
    private Category category;
}
