package app.dtos;

import app.entities.Type;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class TypeDTO
{
    private Long id;
    private TypeInfoDTO type;
    private Long pokemonId;
    private String pokemonName;

    public TypeDTO(TypeInfoDTO type, Long pokemonId, String pokemonName) {
        this.type = type; // Assign the TypeInfoDTO object
        this.pokemonId = pokemonId;
        this.pokemonName = pokemonName;
    }


    // Constructor to create TypeDTO from Type entity
    public TypeDTO(Type type)
    {
        this.type = new TypeInfoDTO(type.getName());  // Map Type entity's name to TypeInfoDTO
        this.pokemonId = type.getPokemon().getId();
        this.pokemonName = type.getPokemon().getName();
    }

//    public TypeDTO(Type type)
//    {
//        this.type = new TypeInfoDTO();
////        this.type.setName(type.getName());
//        this.type = new TypeInfoDTO(type.getName());  // Map TypeInfoDTO using the name from Type
//        this.pokemonId = type.getPokemon().getId();
//        this.pokemonName = type.getPokemon().getName();
//    }

    public String getName()
    {
        return this.type.getName();
    }
}
