package app.routes;


import app.config.HibernateConfig;
import app.controllers.RoomController;
import app.daos.RoomDAO;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

public class RoomRoute
{
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("hotel");
    private final RoomDAO roomDAO = new RoomDAO(emf);
    private final RoomController roomController = new RoomController(roomDAO);

    public EndpointGroup getRoomRoutes()
    {
        return () ->
        {
//            get("/", roomController::getAllRooms);
//            get("/{id}", roomController::getRoomById);
//            post("/", roomController::createRoom);
//            delete("/{id}", roomController::deleteRoom);
//            put("/{id}", roomController::updateRoom);
        };
    }

}
