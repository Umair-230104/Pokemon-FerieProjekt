package app;
//Ingen populator til test
//Ingen test der skal testes for daos og routes
//TODO
// Når man skal oprette pokemons, så skal der checkes om id'et findes i forvejen
// DAO, Controller og Routes til Types

//API link
//https://pokeapi.co/
//https://pokeapi.co/api/v2/pokemon
//https://pokeapi.co/api/v2/pokemon?limit=1302&offset=0
//Vi henter height weight name id og types ned fra APIen

import app.config.AppConfig;

public class Main
{
    public static void main(String[] args)
    {
        AppConfig.startServer();
    }
}
