package app.routes;

import app.config.HibernateConfig;
import app.controllers.PokemonController;
import app.daos.PokemonDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;

public class PokemonRoute
{
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("hotel");
    private final PokemonDAO pokemonDAO = new PokemonDAO(emf);
    private final PokemonController pokemonController = new PokemonController(pokemonDAO);

    public EndpointGroup getPokemonRoutes()
    {
        return () ->
        {
            get("/", pokemonController::getAllPokemons);
            get("/{id}", pokemonController::getPokemonById);
            post("/", pokemonController::createPokemon);
            delete("/{id}", pokemonController::deletePokemon);
            put("/{id}", pokemonController::updatePokemon);
        };
    }
}
