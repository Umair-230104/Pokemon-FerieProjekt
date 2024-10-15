package app.daos;

import app.dtos.PokemonDetailDTO;
import app.entities.Hotel;
import app.entities.Pokemon;
import app.entities.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PokemonDAO implements IDAO
{
    private final EntityManagerFactory emf;

    public PokemonDAO(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public List<Pokemon> getAll()
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.createQuery("SELECT p FROM Pokemon p LEFT JOIN FETCH p.types", Pokemon.class).getResultList();
        }
    }

    @Override
    public Pokemon getById(long id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            return em.find(Pokemon.class, id);
        }
    }

    public void createPokemon(Pokemon pokemon)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(pokemon);
            em.getTransaction().commit();
        }
    }


//    public void saveMoviesToDb(List<MovieDTO> movieDTOList)
//    {
//        try (EntityManager em = emf.createEntityManager())
//        {
//            em.getTransaction().begin();
//            for (MovieDTO movieDTO : movieDTOList)
//            {
//                Movie movie = new Movie();
//                movie.setTitle(movieDTO.getTitle());
//                movie.setOverview(movieDTO.getOverview());
//                movie.setReleaseDate(movieDTO.getReleaseDate());
//                movie.setRating(movieDTO.getRating());
//                movie.setOriginalLanguage(movieDTO.getOriginalLanguage());
//                movie.setPopularity(movieDTO.getPopularity());
//                em.persist(movie);
//            }
//            em.getTransaction().commit();
//        }
//    }


    public void updatePokemon(Pokemon pokemon, PokemonDetailDTO pokemonDetailDTO)
    {
        try (var em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            // Update Pokémon's basic information
            pokemon.setName(pokemonDetailDTO.getName());
            pokemon.setHeight(pokemonDetailDTO.getHeight());
            pokemon.setWeight(pokemonDetailDTO.getWeight());

            pokemon.setTypes(pokemonDetailDTO.getTypes().stream()
                    .map(Type::new)
                    .collect(Collectors.toList()));

            em.merge(pokemon);
            em.getTransaction().commit();
        }
    }

//    public void updatePokemon(Pokemon pokemon, PokemonDetailDTO pokemonDetailDTO)
//    {
//        try (var em = emf.createEntityManager())
//        {
//            em.getTransaction().begin();
//
//            // Update Pokémon's basic information
//            pokemon.setName(pokemonDetailDTO.getName());
//            pokemon.setHeight(pokemonDetailDTO.getHeight());
//            pokemon.setWeight(pokemonDetailDTO.getWeight());
//
//            // Clear the existing types to handle orphan removal
//            pokemon.getTypes().clear();
//
//            // Add the new types from the DTO
//            List<Type> updatedTypes = pokemonDetailDTO.getTypes().stream()
//                    .map(typeDTO ->
//                    {
//                        Type newType = new Type();
//                        newType.setName(typeDTO.getType().getName());
//                        newType.setPokemon(pokemon);  // Important: Set the parent Pokémon
//                        return newType;
//                    })
//                    .collect(Collectors.toList());
//
//            // Set the updated types
//            pokemon.setTypes(updatedTypes);
//
//            // Update the Pokémon entity
//            em.merge(pokemon);
//            em.getTransaction().commit();
//        }
//    }


    @Override
    public Object update(Object o, Object o2)
    {
        return null;
    }

    @Override
    public void delete(long id)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Pokemon pokemon = em.find(Pokemon.class, id);
            em.remove(pokemon);
            em.getTransaction().commit();
        }
    }

    @Override
    public void create(Object o)
    {

    }
}
