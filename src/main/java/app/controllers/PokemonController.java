package app.controllers;

import app.daos.HotelDAO;
import app.daos.PokemonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PokemonController
{
    private final Logger log = LoggerFactory.getLogger(HotelController.class);
    PokemonDAO pokemonDAO;

    public PokemonController(PokemonDAO pokemonDAO)
    {
        this.pokemonDAO = pokemonDAO;
    }

    public void getPokemonById (){

    }

    public void getAllPokemons(){

    }

    public void createPokemon(){

    }

    public void updatePokemon(){

    }

    public void deletePokemon(){

    }

}
