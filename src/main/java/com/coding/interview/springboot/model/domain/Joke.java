package com.coding.interview.springboot.model.domain;

import com.coding.interview.springboot.model.Category;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Access(value=AccessType.FIELD)
public class Joke {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String setup;

    private String delivery;

    private String joke;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Category category;
}
