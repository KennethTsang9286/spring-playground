package com.example.consumingrest.Object;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShortGreeting extends RepresentationModel<ShortGreeting> {
    private final String content;

    @JsonCreator
    public ShortGreeting(@JsonProperty("content") String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}