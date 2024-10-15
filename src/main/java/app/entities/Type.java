package app.entities;

import app.dtos.TypeDTO;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @ManyToOne
    private Pokemon pokemon;

    // Constructor to create Type from TypeDTO
    public Type(TypeDTO typeDTO, Pokemon pokemon)
    {
        this.name = typeDTO.getType().getName();  // Map TypeInfoDTO name
        this.pokemon = pokemon;  // Assuming you have the Pokemon entity mapped here
    }

    public Type(String name, Pokemon pokemon)
    {
        this.name = name;
        this.pokemon = pokemon;
    }

    public Type(TypeDTO typeDTO)
    {
        this.name = typeDTO.getType().getName();
    }

    // src/main/java/app/entities/Type.java
@Override
public String toString() {
    return "Type{" +
            "id=" + id +
            ", name='" + name + '\'' +
            // Exclude pokemon to avoid recursive call
            '}';
}
}
