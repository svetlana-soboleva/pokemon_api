package se.salt.jfs.kata.pokemon.controllers;


import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import se.salt.jfs.kata.pokemon.dtos.PokemonListResponse;

import se.salt.jfs.kata.pokemon.dtos.PokemonResponse;
import se.salt.jfs.kata.pokemon.models.Pokemon;
import se.salt.jfs.kata.pokemon.models.Type;
import se.salt.jfs.kata.pokemon.services.PokemonService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }


    @GetMapping("/pokemon")
    public Mono<PokemonListResponse> getAllPokemon() {
        return pokemonService.getPokemons();
    }

    @GetMapping("/pokemon/{name}")
    public Mono<PokemonResponse> getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name)
                .map(pokemon -> {
                    List<String> typeNames = pokemon.getTypes().stream()
                            .map(type -> type.getTypeDetail().getName())
                            .toList();
                    return new PokemonResponse(pokemon.getId(), pokemon.getName(), pokemon.getHeight(), pokemon.getWeight(), typeNames);
                });
    }


}

