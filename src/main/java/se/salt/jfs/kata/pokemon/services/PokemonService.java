package se.salt.jfs.kata.pokemon.services;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import se.salt.jfs.kata.pokemon.dtos.PokemonListResponse;
import se.salt.jfs.kata.pokemon.models.Pokemon;

@Service
public class PokemonService {

    private final WebClient webClient;

    public PokemonService(WebClient.Builder webClientBuilder) {
        String BASE_URL = "https://pokeapi.co/api/v2/pokemon";
        this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    }


    public Mono<PokemonListResponse> getPokemons() {
        int LIMIT = 50;
        return this.webClient.get()
                .uri("?limit=" + LIMIT)
                .retrieve()
                .bodyToMono(PokemonListResponse.class);
    }


    public Mono<Pokemon> getPokemonByName(String name) {
        return this.webClient.get()
                .uri("/" + name)
                .retrieve()
                .bodyToMono(Pokemon.class);
    }


}

