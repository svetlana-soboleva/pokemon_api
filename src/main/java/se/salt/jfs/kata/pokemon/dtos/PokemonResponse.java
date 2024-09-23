package se.salt.jfs.kata.pokemon.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PokemonResponse(
        int id,
        String name,
        int height,
        int weight,
        @JsonProperty("types")
        List<String> types
) {}

