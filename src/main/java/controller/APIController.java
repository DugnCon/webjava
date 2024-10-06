package main.java.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class APIController {
    private static final String API_KEY = "AIzaSyC1EH7kNUCc-lgollZW7OvpzsJED31s4RM"; // Thay bằng API Key 
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    private HttpClient client;

    public APIController() {
        client = HttpClient.newHttpClient(); // Khởi tạo HttpClient
    }

    // Phương thức tìm kiếm sách theo từ khóa
    public JsonArray searchBooks(String query) throws IOException, InterruptedException {
        String url = BASE_URL + "?q=" + query + "&key=" + API_KEY;

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Kiểm tra mã trạng thái
        if (response.statusCode() == 200) {
            // Phân tích dữ liệu JSON từ phản hồi
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            return jsonObject.getAsJsonArray("items"); // Trả về danh sách sách
        } else {
            throw new IOException("Error fetching books: " + response.statusCode());
        }
    }
}
