package app.routes;

import app.config.HibernateConfig;
import app.controllers.PokemonController;
import app.daos.PokemonDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

public class PokemonRoute
{
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("hotel");
    private final PokemonDAO pokemonDAO = new PokemonDAO(emf);
    private final PokemonController pokemonController = new PokemonController(pokemonDAO);

    public EndpointGroup getPokemonRoutes()
    {
        return () ->
        {
//            get("/", hotelController::getAllHotels);
//            get("/{id}", hotelController::getHotelById);
//            post("/", hotelController::createHotel);
//            delete("/{id}", hotelController::deleteHotel);
//            put("/{id}", hotelController::updateHotel);
//            get("/rooms/{id}", hotelController::roomForSpecificHotel);
        };
    }

}
