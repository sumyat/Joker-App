package com.coding.interview.springboot.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Category {
    @JsonProperty("Misc")
    MISC,
    @JsonProperty("Programming")
    PROGRAMMING,
    @JsonProperty("Dark")
    DARK,
    @JsonProperty("Pun")
    PUN,
    @JsonProperty("Spooky")
    SPOOKY,
    @JsonProperty("Christmas")
    CHRISTMAS;
}
