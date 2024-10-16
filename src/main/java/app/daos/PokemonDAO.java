package app.daos;

import app.dtos.PokemonDetailDTO;
import app.entities.Pokemon;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

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

    public void updatePokemon(PokemonDetailDTO pokemonDetailDTO)
    {
        try (var em = emf.createEntityManager())
        {
            em.getTransaction().begin();

            // Use the constructor to convert PokemonDetailDTO to Pokemon
            Pokemon updatedPokemon = new Pokemon(pokemonDetailDTO);

            // Merge the updated Pokemon entity back into the database
            em.merge(updatedPokemon);
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
//            // Clear current types and set new ones
//            pokemon.getTypes().clear();  // Clear existing types to prevent duplication
//
//            // Map the new types from the DTO and set the Pokemon reference
//            List<Type> updatedTypes = pokemonDetailDTO.getTypes().stream()
//                    .map(typeDTO ->
//                    {
//                        Type type = new Type(typeDTO.getType().getName(), pokemon);  // Set the pokemon reference here
//                        return type;
//                    })
//                    .collect(Collectors.toList());
//
//            // Set the updated types to the Pokemon
//            pokemon.setTypes(updatedTypes);
//
//            // Merge the updated Pokemon entity back into the database
//            em.merge(pokemon);
//            em.getTransaction().commit();
//        }
//    }

    public void savePokemonsToDb(List<PokemonDetailDTO> pokemonDetailDTOList)
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            for (PokemonDetailDTO pokemonDetailDTO : pokemonDetailDTOList)
            {
                Pokemon pokemon = new Pokemon(pokemonDetailDTO);  // Use the constructor to convert PokemonDetailDTO to Pokemon
                em.persist(pokemon);
            }
        }
    }

//    public void savePokemonsToDb(List<PokemonDetailDTO> pokemonDetailDTOList)
//    {
//        try (EntityManager em = emf.createEntityManager())
//        {
//            em.getTransaction().begin();
//            for (PokemonDetailDTO pokemonDetailDTO : pokemonDetailDTOList)
//            {
//                Pokemon pokemon = new Pokemon();
//                pokemon.setId(pokemonDetailDTO.getId());
//                pokemon.setName(pokemonDetailDTO.getName());
//                pokemon.setHeight(pokemonDetailDTO.getHeight());
//                pokemon.setWeight(pokemonDetailDTO.getWeight());
//
//                // Create Type entities and associate them with the Pokemon
//                List<Type> types = pokemonDetailDTO.getTypes().stream()
//                        .map(typeDTO -> new Type(typeDTO, pokemon))  // Pass the Pokemon entity to the Type, including its name
//                        .collect(Collectors.toList());
//
//                pokemon.setTypes(types);
//                em.persist(pokemon);
//            }
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
