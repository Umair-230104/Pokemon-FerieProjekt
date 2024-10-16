package app.controllers;

import app.daos.PokemonDAO;
import app.dtos.PokemonDetailDTO;
import app.entities.Pokemon;
import app.exception.ApiException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

//Jeg kan hente alt ned men jeg får ikke id'er på types

public class PokemonController
{
    private final Logger log = LoggerFactory.getLogger(HotelController.class);
    PokemonDAO pokemonDAO;

    public PokemonController(PokemonDAO pokemonDAO)
    {
        this.pokemonDAO = pokemonDAO;
    }

    public void getPokemonById(Context ctx)
    {
        try
        {
            // == request ==
            long id = Long.parseLong(ctx.pathParam("id"));

            // == querying ==
            Pokemon pokemon = pokemonDAO.getById(id);

            // == response ==
            PokemonDetailDTO pokemonDetailDTO = new PokemonDetailDTO(pokemon);
            ctx.res().setStatus(200);
            ctx.json(pokemonDetailDTO, PokemonDetailDTO.class);

        } catch (NumberFormatException e)
        {
            // Log an error if there is an error
            log.error("400 {} ", e.getMessage());

            // Throw our own exception, which we created in ApiException.java
            throw new ApiException(400, e.getMessage());
        }
    }

    public void getAllPokemons(Context ctx)
    {
        try
        {
            // == querying ==
            List<Pokemon> pokemons = pokemonDAO.getAll();

            // == response ==
            List<PokemonDetailDTO> pokemonDetailDTOs = PokemonDetailDTO.toPokemonDetailDTOList(pokemons);

            ctx.res().setStatus(200);
            ctx.json(pokemonDetailDTOs, PokemonDetailDTO.class);
        } catch (Exception e)
        {
            log.error("500 {} ", e.getMessage());
            throw new ApiException(500, e.getMessage());
        }

    }

    public void createPokemon(Context ctx)
    {
        try
        {
            // == request ==
            PokemonDetailDTO pokemonDetailDTO = ctx.bodyAsClass(PokemonDetailDTO.class);

            // == querying ==
            Pokemon pokemon = new Pokemon(pokemonDetailDTO);
            pokemonDAO.createPokemon(pokemon);

            // == response ==
            ctx.res().setStatus(201);
            ctx.result("Pokemon created");
        } catch (Exception e)
        {
            // Log an error if there is an error
            log.error("400 {} ", e.getMessage());

            // Throw our own exception, which we created in ApiException.java
            throw new ApiException(400, e.getMessage());
        }

    }

    public void updatePokemon(Context ctx)
    {
        try
        {
            // == request ==
            long id = Long.parseLong(ctx.pathParam("id"));
            PokemonDetailDTO pokemonDetailDTO = ctx.bodyAsClass(PokemonDetailDTO.class);

            // == querying ==
            Pokemon pokemon = pokemonDAO.getById(id);
            pokemonDAO.updatePokemon(pokemonDetailDTO);

            // == response ==
            ctx.res().setStatus(200);
            ctx.result("Pokemon updated");
        } catch (NumberFormatException e)
        {
            // Log an error if there is an error
            log.error("400 {} ", e.getMessage());

            // Throw our own exception, which we created in ApiException.java
            throw new ApiException(400, e.getMessage());
        } catch (Exception e)
        {
            // Log an error if there is an error
            log.error("500 {} ", e.getMessage());

            // Throw our own exception, which we created in ApiException.java
            throw new ApiException(500, e.getMessage());
        }
    }

    public void deletePokemon(Context ctx)
    {
        try
        {
            // == request ==
            long id = Long.parseLong(ctx.pathParam("id"));

            // == querying ==
            Pokemon pokemon = pokemonDAO.getById(id);

            // == response ==
            pokemonDAO.delete(id);
            ctx.res().setStatus(200);
            ctx.result("Pokemon deleted");
        } catch (NumberFormatException e)
        {
            // Log an error if there is an error
            log.error("400 {} ", e.getMessage());

            // Throw our own exception, which we created in ApiException.java
            throw new ApiException(400, e.getMessage());
        } catch (Exception e)
        {
            // Log an error if there is an error
            log.error("500 {} ", e.getMessage());

            // Throw our own exception, which we created in ApiException.java
            throw new ApiException(500, e.getMessage());
        }
    }
}
