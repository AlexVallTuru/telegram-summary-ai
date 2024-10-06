package com.example.telegram_summary_ai.aiIntegration.client;

import com.example.telegram_summary_ai.aiIntegration.model.GPTResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPTClient {

    private static final String GPT_API_URL = "https://api.openai.com/v1/chat/completions";
    private final ApiClient apiClient;
    private final String apiKey;
    private final ObjectMapper objectMapper;

    public GPTClient() {
        this.apiClient = new ApiClient();
        this.apiKey = System.getenv("GPT_API_KEY");
        this.objectMapper = new ObjectMapper();
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    public String getCompletion(String prompt) throws ApiException, IOException, InterruptedException {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("messages", new Object[]{
                new HashMap<String, String>() {{
                    put("role", "user");
                    put("content", prompt);
                }}
        });
        requestBody.put("max_tokens", 1000);
        requestBody.put("temperature", 0.2);
        requestBody.put("n", 1);
        requestBody.put("stop", List.of("\n\n"));

        String jsonBody = objectMapper.writeValueAsString(requestBody);

        String jsonResponse = apiClient.sendPost(GPT_API_URL, jsonBody, apiKey);

        GPTResponse gptResponse = objectMapper.readValue(jsonResponse, GPTResponse.class);

        return gptResponse.getChoices().get(0).getMessage().getContent();
    }

}
