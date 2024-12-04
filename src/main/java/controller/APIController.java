package main.java.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIController {
    private static final String API_KEY = "AIzaSyC1EH7kNUCc-lgollZW7OvpzsJED31s4RM";
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";
    private HttpClient client;
    private ExecutorService executor;

    public APIController() {
        client = HttpClient.newHttpClient();
        executor = Executors.newFixedThreadPool(20);
    }

    // Truy vấn sách từ Google Books API
    public CompletableFuture<JsonArray> searchBooksAsync(String query, int maxResults) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return searchBooks(query, maxResults);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error during searchBooks", e);
            }
        }, executor);
    }

    public CompletableFuture<JsonArray> searchBooksFromExternalAPI(String query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return searchBooksFromOpenLibrary(query);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error during OpenLibrary search", e);
            }
        }, executor);
    }

    JsonArray searchBooks(String query, int maxResults) throws IOException, InterruptedException {
        String url = BASE_URL + "?q=" + query + "&key=" + API_KEY + "&maxResults=" + maxResults;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            return jsonObject.has("items") ? jsonObject.getAsJsonArray("items") : new JsonArray();
        } else {
            throw new IOException("Error fetching books: " + response.statusCode());
        }
    }

    JsonArray searchBooksFromOpenLibrary(String query) throws IOException, InterruptedException {
        String url = "https://openlibrary.org/search.json?q=" + query;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            return jsonObject.has("docs") ? jsonObject.getAsJsonArray("docs") : new JsonArray();
        } else {
            throw new IOException("Error fetching books from OpenLibrary: " + response.statusCode());
        }
    }

    public CompletableFuture<JsonArray> searchBooksAsyncCombined(String query) {
        CompletableFuture<JsonArray> googleBooksFuture = searchBooksAsync(query, 10);
        CompletableFuture<JsonArray> openLibraryFuture = searchBooksFromExternalAPI(query);

        return googleBooksFuture.thenCombine(openLibraryFuture, (googleBooks, openLibraryBooks) -> mergeResults(googleBooks, openLibraryBooks));
    }

    public JsonArray mergeResults(JsonArray googleBooks, JsonArray openLibraryBooks) {
        JsonArray combinedResults = new JsonArray();

        for (JsonElement googleBook : googleBooks) {
            combinedResults.add(googleBook);
        }

        for (JsonElement openLibraryBook : openLibraryBooks) {
            JsonObject transformedBook = transformOpenLibraryBook(openLibraryBook.getAsJsonObject());
            combinedResults.add(transformedBook);
        }

        return combinedResults;
    }

    public JsonObject transformOpenLibraryBook(JsonObject openLibraryBook) {
        JsonObject book = new JsonObject();
        book.addProperty("title", openLibraryBook.has("title") ? openLibraryBook.get("title").getAsString() : "Unknown Title");
        book.addProperty("infoLink", openLibraryBook.has("key") ? "https://openlibrary.org" + openLibraryBook.get("key").getAsString() : "");

        if (openLibraryBook.has("cover_i")) {
            String imageUrl = "https://covers.openlibrary.org/b/id/" + openLibraryBook.get("cover_i").getAsInt() + "-M.jpg";
            JsonObject imageLinks = new JsonObject();
            imageLinks.addProperty("thumbnail", imageUrl);
            book.add("imageLinks", imageLinks);
        }

        return book;
    }

    public void shutdown() {
        executor.shutdown();
    }
} 