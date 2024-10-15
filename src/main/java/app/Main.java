package app;

//Ingen populator til test
//Ingen test der skal testes for daos og routes
//PokemonService klasserne skal laves udefra hvordan de er implementeret i de movie projektet
//DAO metoderne returnere "void" kig på hvordan det lavet andre steder

//TODO
// Når man skal oprette pokemons, så skal der checkes om id'et findes i forvejen

//API link
//https://pokeapi.co/
//https://pokeapi.co/api/v2/pokemon
//https://pokeapi.co/api/v2/pokemon?limit=1302&offset=0
//Vi henter height weight name id og types ned fra APIen

import app.config.HibernateConfig;
import app.daos.PokemonDAO;
import app.dtos.PokemonDetailDTO;
import app.dtos.TypeDTO;
import app.dtos.TypeInfoDTO;
import app.entities.Pokemon;
import app.entities.Type;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("pokemondb");
        //        AppConfig.startServer();


        PokemonDAO pokemonDAO = new PokemonDAO(emf);
        List<Type> types = new ArrayList<>(); // Assuming TypeDTO is the expected type
        Pokemon pokemon = new Pokemon(122222L, 1, 1, "bulbasaur", types);
        Type type = new Type("Grass", pokemon);
        types.add(type);


        pokemonDAO.createPokemon(pokemon);


//        System.out.println(pokemonDAO.getById(122222L));
//        pokemonDAO.delete(122222L);

        // Assume the Pokemon with ID 122222L already exists in the database
        Long pokemonId = 122222L;

        // 1. Retrieve the existing Pokemon by ID
        Pokemon existingPokemon = pokemonDAO.getById(pokemonId);

        if (existingPokemon != null)
        {
            // 2. Create a PokemonDetailDTO with new information
            // 2. Create a PokemonDetailDTO with new information
            List<TypeDTO> updatedTypes = List.of(
                    new TypeDTO(new TypeInfoDTO("Fire"), null, null),   // Create a TypeDTO with TypeInfoDTO
                    new TypeDTO(new TypeInfoDTO("Flying"), null, null)  // Create another TypeDTO with TypeInfoDTO
            );
            PokemonDetailDTO updatedPokemonDetailDTO = new PokemonDetailDTO(
                    pokemonId,        // Keep the same ID
                    10,               // Updated height
                    12,               // Updated weight
                    "UpdatedName",    // Updated name
                    updatedTypes      // Updated types
            );


            // 3. Call the updatePokemon method to update the existing Pokemon
            pokemonDAO.updatePokemon(existingPokemon, updatedPokemonDetailDTO);

            // 4. Fetch the updated Pokemon and print it to check if the update was successful
            Pokemon updatedPokemon = pokemonDAO.getById(pokemonId);
            System.out.println("Updated Pokemon: " + updatedPokemon);
        } else
        {
            System.out.println("Pokemon with ID " + pokemonId + " not found.");
        }

        emf.close();

    }
}
