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
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main
{
    public static void main(String[] args) throws IOException, URISyntaxException, InterruptedException
    {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("pokemondb");
        //        AppConfig.startServer();

    }
}
