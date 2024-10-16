package app.service;

import app.daos.PokemonDAO;
import app.dtos.PokemonDTO;
import app.dtos.PokemonDTOResponse;
import app.dtos.PokemonDetailDTO;
import app.dtos.TypeDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PokemonService
{
    private static final String BASE_URL_Pokemon = "https://pokeapi.co/api/v2/pokemon/";
    public static final List<PokemonDTO> pokemonDTOList = new ArrayList<>();
    private PokemonDAO pokemonDAO;

    public PokemonService(PokemonDAO pokemonDAO)
    {
        this.pokemonDAO = pokemonDAO;
    }

    public void getPokemonsToDB() throws IOException, InterruptedException, URISyntaxException
    {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(BASE_URL_Pokemon))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200)
        {
            String json = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            PokemonDTOResponse pokemonDTOResponse = objectMapper.readValue(json, PokemonDTOResponse.class);

            if (pokemonDTOResponse.getPokemons() != null)
            {
                List<PokemonDetailDTO> pokemonDetailDTOList = new ArrayList<>();

                // Fetch detailed Pokémon data for each Pokémon
                for (PokemonDTO pokemonDTO : pokemonDTOResponse.getPokemons())
                {
                    String pokemonUrl = pokemonDTO.getUrl();
                    HttpRequest detailRequest = HttpRequest.newBuilder()
                            .uri(URI.create(pokemonUrl))
                            .GET()
                            .build();
                    HttpResponse<String> detailResponse = client.send(detailRequest, HttpResponse.BodyHandlers.ofString());

                    if (detailResponse.statusCode() == 200)
                    {
                        String detailJson = detailResponse.body();
                        PokemonDetailDTO pokemonDetail = objectMapper.readValue(detailJson, PokemonDetailDTO.class);
                        pokemonDetailDTOList.add(pokemonDetail);
                    }
                }

                // Save all Pokémon details to the database
                savePokemonsToDb(pokemonDetailDTOList);
            }
        }
    }

    // Method to save a list of Pokémon details to the database
    private void savePokemonsToDb(List<PokemonDetailDTO> pokemonDetailDTOList)
    {
        pokemonDAO.savePokemonsToDb(pokemonDetailDTOList);
    }

    public void getPokemonById(int id) throws IOException, InterruptedException, URISyntaxException
    {
        HttpClient client = HttpClient.newHttpClient();

        // Send request for the Pokémon by ID
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(BASE_URL_Pokemon + id))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200)
        {
            String json = response.body();
            ObjectMapper objectMapper = new ObjectMapper();
            PokemonDetailDTO pokemonDetail = objectMapper.readValue(json, PokemonDetailDTO.class);

            StringBuilder output = new StringBuilder();
            output.append("Id: ").append(pokemonDetail.getId()).append("\n")
                    .append("Name: ").append(pokemonDetail.getName()).append("\n")
                    .append("Height: ").append(pokemonDetail.getHeight()).append("\n")
                    .append("Weight: ").append(pokemonDetail.getWeight()).append("\n")
                    .append("Types: ").append("\n");

            // Iterate over types and append each to a new line
            for (TypeDTO type : pokemonDetail.getTypes())
            {
                output.append("- ").append(type.getType().getName()).append("\n");
            }

            System.out.println(output);
        } else
        {
            System.out.println("GET request failed. Status code: " + response.statusCode());
        }
    }

    public void getPokemons() throws IOException, InterruptedException, URISyntaxException
    {
        HttpClient client = HttpClient.newHttpClient();

        // Send request for the current page
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(BASE_URL_Pokemon))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200)
        {
            String json = response.body();
            // System.out.println("Raw JSON response: " + json); // Print the raw JSON response
            ObjectMapper objectMapper = new ObjectMapper();
            PokemonDTOResponse pokemonDTOResponse = objectMapper.readValue(json, PokemonDTOResponse.class);

            if (pokemonDTOResponse.getPokemons() != null)
            {
                // Add fetched Pokémon to the list
                for (PokemonDTO pokemonDTO : pokemonDTOResponse.getPokemons())
                {
                    pokemonDTOList.add(pokemonDTO);
                }

                // Print Pokémon with detail
                for (PokemonDTO pokemonDTO : pokemonDTOResponse.getPokemons())
                {
                    StringBuilder output = new StringBuilder();

                    // Hent detaljer om hver Pokémon
                    String pokemonUrl = pokemonDTO.getUrl();  // URL'en til detaljer

                    // Send anmodning for at få detaljer om Pokémon
                    HttpRequest detailRequest = HttpRequest.newBuilder()
                            .uri(URI.create(pokemonUrl))
                            .GET()
                            .build();
                    HttpResponse<String> detailResponse = client.send(detailRequest, HttpResponse.BodyHandlers.ofString());
                    if (detailResponse.statusCode() == 200)
                    {
                        String detailJson = detailResponse.body();
                        PokemonDetailDTO pokemonDetail = objectMapper.readValue(detailJson, PokemonDetailDTO.class);

                        output
                                .append("URL: ").append(pokemonDTO.getUrl()).append("\n")
                                .append("Id: ").append(pokemonDetail.getId()).append("\n")
                                .append("Name: ").append(pokemonDTO.getName()).append("\n")
                                .append("Height: ").append(pokemonDetail.getHeight()).append("\n")
                                .append("Weight: ").append(pokemonDetail.getWeight()).append("\n")
                                .append("Types: ").append("\n");

                        // Iterér over typer og føj hver til en ny linje
                        for (TypeDTO type : pokemonDetail.getTypes())
                        {
                            output.append("- ").append(type.getType().getName()).append("\n");
                        }

                        output.append("\n");

                        System.out.println(output);

                    } else
                    {
                        System.out.println("Failed to get details for " + pokemonDTO.getName());
                    }
                }
            } else
            {
                System.out.println("No pokemons found in the response.");
            }
        } else
        {
            System.out.println("GET request failed. Status code: " + response.statusCode());
        }
    }
}