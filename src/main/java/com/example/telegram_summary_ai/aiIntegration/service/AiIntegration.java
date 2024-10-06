package com.example.telegram_summary_ai.aiIntegration.service;

import com.example.telegram_summary_ai.aiIntegration.client.ApiException;
import com.example.telegram_summary_ai.aiIntegration.client.GPTClient;

import java.io.IOException;

public class AiIntegration {

    private final GPTClient gptApiClient;

    public AiIntegration(){
        this.gptApiClient = new GPTClient();
    }

    public String getLLMResponse(String message) throws IOException, InterruptedException, ApiException {

        if (message == null || message.trim().isEmpty()) {
            return "No se ha recibido correctamente el texto. Texto recibido: " + message;
        }

        String systemPrompt = "Hazme un resumen en 5 puntos clave de los mensajes. Destaca las cosas más importantes, como pagos pendientes, rankings y cualquier otro detalle relevante.";
        return gptApiClient.getCompletion(systemPrompt + message);
    }


}
