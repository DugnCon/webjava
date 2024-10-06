package main.java.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.concurrent.CompletableFuture;

public class MultiThreadedAPIController {
    private APIController apiController;

    public MultiThreadedAPIController() {
        apiController = new APIController(); // Khởi tạo APIController
    }

    // Phương thức tìm kiếm sách trong một luồng không đồng bộ
    public CompletableFuture<JsonArray> searchBooks(String query) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return apiController.searchBooks(query);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e); // Ném lỗi để CompletableFuture có thể xử lý
            }
        });
    }
}
