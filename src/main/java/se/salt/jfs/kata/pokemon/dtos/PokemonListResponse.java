package se.salt.jfs.kata.pokemon.dtos;

import se.salt.jfs.kata.pokemon.models.Pokemon;

import java.util.List;

public record PokemonListResponse(List<PokemonResponse> results) {
}