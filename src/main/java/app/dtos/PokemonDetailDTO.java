package app.dtos;

import app.entities.Pokemon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class PokemonDetailDTO
{
    private Long id;
    private int height;
    private int weight;
    private String name;
    private List<TypeDTO> types;

    public PokemonDetailDTO(Pokemon pokemon)
    {
        this.id = pokemon.getId();
        this.height = pokemon.getHeight();
        this.weight = pokemon.getWeight();
        this.name = pokemon.getName();
        this.types = pokemon.getTypes().stream().map(TypeDTO::new).toList();
    }

    public static List<PokemonDetailDTO> toPokemonDetailDTOList(List<Pokemon> pokemons)
    {
        return pokemons.stream().map(PokemonDetailDTO::new).collect(Collectors.toList());
    }
}
