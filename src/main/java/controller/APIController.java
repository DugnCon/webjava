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
    private static final String API_KEY = "AIzaSyC1EH7kNUCc-lgollZW7OvpzsJED31s4RM";
    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes";

    private HttpClient client;

    public APIController() {
        client = HttpClient.newHttpClient();
    }

    public JsonArray searchBooks(String query) throws IOException, InterruptedException {
        String url = BASE_URL + "?q=" + query + "&key=" + API_KEY;

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            return jsonObject.getAsJsonArray("items");
        } else {
            throw new IOException("Error fetching books: " + response.statusCode());
        }
    }

    public JsonArray getSuggestions(String query) throws IOException, InterruptedException {
    	/**Giới hạn số lượng kết quả*/
        String url = BASE_URL + "?q=" + query + "&key=" + API_KEY + "&maxResults=5";
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
            return jsonObject.getAsJsonArray("items");
        } else {
            throw new IOException("Error fetching suggestions: " + response.statusCode());
        }
    }
}
