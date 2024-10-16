package app.entities;

import app.dtos.TypeDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonIgnore  // Prevent this field from being serialized directly
    private String name;
    @ManyToOne
    private Pokemon pokemon;

    // New column to store the Pokémon's name directly
    private String pokemonName;

    // Constructor to create Type from TypeDTO
    public Type(TypeDTO typeDTO, Pokemon pokemon)
    {
//        this.name = typeDTO.getType().getName();  // Map TypeInfoDTO name
        this.pokemon = pokemon;  // Assuming you have the Pokemon entity mapped here
        this.pokemonName = pokemon.getName();  // Set the Pokémon's name directly

    }

    public Type(String name, Pokemon pokemon)
    {
        this.name = name;
        this.pokemon = pokemon;
        this.pokemonName = pokemon.getName();  // Set the Pokémon's name directly

    }

    public Type(TypeDTO typeDTO)
    {
        this.name = typeDTO.getType().getName();
    }
}
