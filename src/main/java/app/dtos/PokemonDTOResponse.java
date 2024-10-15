package app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class PokemonDTOResponse
{
    private int count;
    private String next;

    @JsonProperty("results")
    private List<PokemonDTO> pokemons;
}

