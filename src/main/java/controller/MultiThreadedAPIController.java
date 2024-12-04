package main.java.controller;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class MultiThreadedAPIController {
    private APIController apiController;
    private ExternalAPIController externalAPIController;
    private ExecutorService executor;

    public MultiThreadedAPIController() {
        apiController = new APIController(); 
        externalAPIController = new ExternalAPIController();
        executor = Executors.newFixedThreadPool(20);
    }

    public CompletableFuture<JsonArray> searchBooksAsync(String query) {
        CompletableFuture<JsonArray> googleBooksFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return apiController.searchBooks(query,10);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error during Google Books search", e);
            }
        }, executor);

        CompletableFuture<JsonArray> openLibraryFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return externalAPIController.searchBooksFromOpenLibrary(query);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error during OpenLibrary search", e);
            }
        }, executor);

        return googleBooksFuture.thenCombine(openLibraryFuture, (googleBooks, openLibraryBooks) -> mergeResults(googleBooks, openLibraryBooks));
    }

    private JsonArray mergeResults(JsonArray googleBooks, JsonArray openLibraryBooks) {
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

    private JsonObject transformOpenLibraryBook(JsonObject openLibraryBook) {
        JsonObject book = new JsonObject();
        book.addProperty("title", openLibraryBook.has("title") ? openLibraryBook.get("title").getAsString() : "Unknown Title");
        book.addProperty("infoLink", openLibraryBook.has("key") ? "https://openlibrary.org" + openLibraryBook.get("key").getAsString() : "");
        
        /**Nếu API cung cấp URL hình ảnh, hãy thêm*/
        if (openLibraryBook.has("cover_i")) {
            String imageUrl = "https://covers.openlibrary.org/b/id/" + openLibraryBook.get("cover_i").getAsInt() + "-M.jpg";
            JsonObject imageLinks = new JsonObject();
            imageLinks.addProperty("thumbnail", imageUrl);
            book.add("imageLinks", imageLinks);
        }
        
        return book;
    }

    public CompletableFuture<JsonArray> getSuggestionsAsync(String query) {
        return searchBooksAsync(query);
    }

    public void shutdown() {
        executor.shutdown();
    }
}