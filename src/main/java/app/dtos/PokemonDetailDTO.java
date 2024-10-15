package app.dtos;

import app.entities.Pokemon;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public PokemonDetailDTO(Long id, int height, int weight, String name, List<TypeDTO> types)
    {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.types = types;
    }

    public PokemonDetailDTO(String name, int height, int weight, List<TypeDTO> types)
    {
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.types = types;
    }

    public PokemonDetailDTO(Pokemon pokemon)
    {
        this.id = pokemon.getId();
        this.height = pokemon.getHeight();
        this.weight = pokemon.getWeight();
        this.name = pokemon.getName();
        this.types = pokemon.getTypes().stream().map(TypeDTO::new).toList();
    }

}
