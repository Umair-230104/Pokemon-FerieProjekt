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

    // Constructor to create TypeDTO from Type entity
    public TypeDTO(Type type)
    {
        this.id = type.getId();
        this.name = type.getName();  // Map direkte fra Type-entitetens name
        this.pokemonId = type.getPokemon().getId();
        this.pokemonName = type.getPokemon().getName();
    }
}
