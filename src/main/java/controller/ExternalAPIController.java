package main.java.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ExternalAPIController {
    private HttpClient client;

    public ExternalAPIController() {
        client = HttpClient.newHttpClient();
    }

    public CompletableFuture<JsonArray> searchBooksFromOpenLibraryAsync(String query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return searchBooksFromOpenLibrary(query);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error fetching books from OpenLibrary", e);
            }
        });
    }

    public JsonArray searchBooksFromOpenLibrary(String query) throws IOException, InterruptedException {
        String url = "https://openlibrary.org/search.json?q=" + query;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            return jsonObject.getAsJsonArray("docs"); 
        } else {
            throw new IOException("Error fetching books from OpenLibrary: " + response.statusCode());
        }
    }
}
