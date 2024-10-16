package app.dtos;

import app.entities.Type;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class TypeDTO
{
    @JsonProperty("id")
    private Long id;

    @JsonIgnore
    private TypeInfoDTO type;

    @JsonProperty("name")
    private String name;  // Tilføj name direkte i TypeDTO


    // JsonIgnore er en annotation fra Jackson, som fortæller Jackson at den ikke skal vise disse felter i JSON outputtet
    @JsonIgnore
    private Long pokemonId;
    @JsonIgnore
    private String pokemonName;

//    public TypeDTO(TypeInfoDTO type, Long pokemonId, String pokemonName) {
//        this.type = type; // Assign the TypeInfoDTO object
//        this.pokemonId = pokemonId;
//        this.pokemonName = pokemonName;
//    }

    // Constructor to create TypeDTO from Type entity
    public TypeDTO(Type type) {
        this.id = type.getId();
        this.name = type.getName();  // Map direkte fra Type-entitetens name
        this.pokemonId = type.getPokemon().getId();
        this.pokemonName = type.getPokemon().getName();
    }

//    // Constructor to create TypeDTO from Type entity
//    public TypeDTO(Type type)
//    {
//        this.id = type.getId();
//        this.type = new TypeInfoDTO(type.getName());  // Map Type entity's name to TypeInfoDTO
//        this.pokemonId = type.getPokemon().getId();
//        this.pokemonName = type.getPokemon().getName();
//    }

//    public TypeDTO(Type type)
//    {
//        this.type = new TypeInfoDTO();
////        this.type.setName(type.getName());
//        this.type = new TypeInfoDTO(type.getName());  // Map TypeInfoDTO using the name from Type
//        this.pokemonId = type.getPokemon().getId();
//        this.pokemonName = type.getPokemon().getName();
//    }

//    public String getName() {
//        if (this.type != null) {
//            return this.type.getName();
//        } else {
//            return "Unknown"; // or handle this case accordingly
//        }
//    }

}
