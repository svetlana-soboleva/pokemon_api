package se.salt.jfs.kata.pokemon.controllers;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;
import se.salt.jfs.kata.pokemon.models.Pokemon;
import se.salt.jfs.kata.pokemon.models.Type;
import se.salt.jfs.kata.pokemon.models.TypeDetail;
import se.salt.jfs.kata.pokemon.services.PokemonService;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PokemonController.class)
class PokemonControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PokemonService pokemonService;

    private static final Pokemon MOCK_POKEMON;

    static {
        TypeDetail grassDetail = new TypeDetail();
        grassDetail.setDetail("grass");
        grassDetail.setUrl("https://pokeapi.co/api/v2/type/12/");

        Type grassType = new Type();
        grassType.setSlot(1);
        grassType.setTypeDetail(grassDetail);

        TypeDetail poisonDetail = new TypeDetail();
        poisonDetail.setDetail("poison");
        poisonDetail.setUrl("https://pokeapi.co/api/v2/type/4/");

        Type poisonType = new Type();
        poisonType.setSlot(2);
        poisonType.setTypeDetail(poisonDetail);

        MOCK_POKEMON = new Pokemon();
        MOCK_POKEMON.setId(1);
        MOCK_POKEMON.setName("bulbasaur");
        MOCK_POKEMON.setHeight(7);
        MOCK_POKEMON.setWeight(69);
        MOCK_POKEMON.setTypes(List.of(grassType, poisonType));
    }

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnPokemonOnRequest() throws Exception {
        // Arrange
        when(pokemonService.getPokemonByName("bulbasaur")).thenReturn(Mono.just(MOCK_POKEMON));

        // Act & Assert
        mvc.perform(get("/pokemon/bulbasaur")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(MOCK_POKEMON.getName()))
                .andExpect(jsonPath("$.height").value(MOCK_POKEMON.getHeight()))
                .andExpect(jsonPath("$.weight").value(MOCK_POKEMON.getWeight()))
                .andExpect(jsonPath("$.types[0].typeDetail.detail").value("grass"))
                .andExpect(jsonPath("$.types[1].typeDetail.detail").value("poison"));
    }
}