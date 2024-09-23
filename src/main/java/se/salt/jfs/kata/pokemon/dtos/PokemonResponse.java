package se.salt.jfs.kata.pokemon.dtos;

import java.util.List;

public record PokemonResponse(
        int id,
        String name,
        int height,
        int weight,
        List<String> types
) {}

