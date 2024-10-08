package main.java.controller;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.JsonArray;

public class MultiThreadedAPIController {
    private APIController apiController;
    private ExecutorService executor;

    public MultiThreadedAPIController() {
        apiController = new APIController(); 
        executor = Executors.newFixedThreadPool(10);
    }

    public CompletableFuture<JsonArray> searchBooksAsync(String query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return apiController.searchBooks(query);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error during book search", e);
            }
        }, executor);
    }

    public CompletableFuture<JsonArray> getSuggestionsAsync(String query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
               
                return apiController.searchBooks(query); 
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException("Error during getting book suggestions", e);
            }
        }, executor);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
