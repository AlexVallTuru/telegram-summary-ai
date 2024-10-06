package com.example.telegram_summary_ai.aiIntegration.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApiClient {
    private static final Logger logger = Logger.getLogger(ApiClient.class.getName());
    private final HttpClient httpClient;

    public ApiClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String sendPost(String url, String jsonBody, String apiKey) throws IOException, InterruptedException, ApiException {
        logger.log(Level.INFO, "Enviando petición a la URL: " + url);
        logger.log(Level.INFO, "Apikey: " + apiKey);
        logger.log(Level.INFO, "Cuerpo de la petición en JSON: " + jsonBody);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());

        logger.log(Level.INFO, "Respuesta del servidor - Código de estado: " + response.statusCode());
        logger.log(Level.INFO, "Cuerpo de la respuesta en JSON: " + response.body());

        if (response.statusCode() == 200) {
            return response.body();
        } else {
            throw new ApiException("Error en la solicitud: " + response.statusCode() + " - " + response.body(), response.statusCode());
        }
    }
}
