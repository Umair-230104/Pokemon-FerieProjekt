package app.entities;

import app.dtos.PokemonDetailDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor

public class Pokemon
{
    @Id
    private Long id;
    private int height;
    private int weight;
    private String name;

    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Type> types;

    public Pokemon(Long id, int height, int weight, String name, List<Type> types)
    {
        this.id = id;
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.types = types;
    }

    public Pokemon(int height, int weight, String name, List<Type> types)
    {
        this.height = height;
        this.weight = weight;
        this.name = name;
        this.types = types;
    }

    public Pokemon(PokemonDetailDTO pokemonDetailDTO)
    {
        this.id = pokemonDetailDTO.getId();
        this.height = pokemonDetailDTO.getHeight();
        this.weight = pokemonDetailDTO.getWeight();
        this.name = pokemonDetailDTO.getName();
        this.types = pokemonDetailDTO.getTypes().stream()
                .map(typeDTO -> new Type(typeDTO, this)) // Link each Type to this Pokemon
                .collect(Collectors.toList());
    }

//    public Pokemon(PokemonDetailDTO pokemonDetailDTO)
//    {
//        this.id = pokemonDetailDTO.getId();
//        this.height = pokemonDetailDTO.getHeight();
//        this.weight = pokemonDetailDTO.getWeight();
//        this.name = pokemonDetailDTO.getName();
//        this.types = pokemonDetailDTO.getTypes().stream().map(Type::new).collect(Collectors.toList());
//    }

    @Override
    public String toString()
    {
        return "Pokemon{" +
                "id=" + id +
                ", height=" + height +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", types: " + types.stream()
                .map(type -> type.getName() + " Id: " + type.getId())
                .collect(Collectors.joining(", ")) +
                '}';
    }
}
