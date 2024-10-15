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
import app.entities.Pokemon;
import app.entities.Type;
import app.service.PokemonService;
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

    }
}
