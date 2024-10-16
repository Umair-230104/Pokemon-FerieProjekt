package app;

//Ingen populator til test
//Ingen test der skal testes for daos og routes
//TODO
// Når man skal oprette pokemons, så skal der checkes om id'et findes i forvejen
// Når man henter pokemons, så er der ikke id på types fix det
// DAO, Controller og Routes til Types

//API link
//https://pokeapi.co/
//https://pokeapi.co/api/v2/pokemon
//https://pokeapi.co/api/v2/pokemon?limit=1302&offset=0
//Vi henter height weight name id og types ned fra APIen

import app.config.AppConfig;
import app.config.HibernateConfig;
import app.daos.PokemonDAO;
import app.service.PokemonService;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.net.URISyntaxException;


public class Main
{
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("pokemondb");
        AppConfig.startServer();

        PokemonService pokemonService = new PokemonService(new PokemonDAO(emf));
//        pokemonService.getPokemonsToDB();

        PokemonDAO pokemonDAO = new PokemonDAO(emf);
//        pokemonDAO.getAll().forEach(System.out::println);

    }
}
