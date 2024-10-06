package com.example.telegram_summary_ai;

import com.example.telegram_summary_ai.aiIntegration.service.AiIntegration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramSummaryAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramSummaryAiApplication.class, args);
		AiIntegration ai = new AiIntegration();
		String text = "[23/9/24, 04:39:23] Eric (Ecros): \uD83C\uDF05 LEADERBOARD \uD83C\uDF05\n" +
                "Top 1: Violeta 1330 puntos.\n" +
                "Top 2: Eric 333 puntos.\n" +
                "Top 3: -------------\n" +
                "Top 4: -------------\n" +
                "Top 5: -------------\n" +
                "Top 6: -------------\n" +
                "Top 7: -------------\n" +
                "Comentario: Tercera semana, tenemos un nuevo top 1... Casi 200k pasos en una semana y entrenos diarios, incredible performance. Veamos que tal se desarrollan las semanas que quedan para el resto.\n" +
                "[26/9/24, 21:53:56] Eric (Ecros): Mañana pasaros todos pls\n" +
                "[30/9/24, 10:17:40] Eric (Ecros): Espero que me estéis apuntando los puñeteros pesos\n" +
                "[30/9/24, 10:17:48] Eric (Ecros): Esta noche publicaré los resultados de la semana 4\n" +
                "[30/9/24, 10:17:58] Eric (Ecros): Stay tuned\n" +
                "[30/9/24, 10:24:33] Marti: (Manténgansa tuneados)\n" +
                "[30/9/24, 10:39:46] Marti: Handsome\n" +
                "[30/9/24, 23:28:20] Eric (Ecros): \uD83C\uDF05 LEADERBOARD \uD83C\uDF05\n" +
                "\uD83D\uDE08Top 1: Martí 1500 \uD83D\uDE08\n" +
                "Top 2: Ciro 1370\n" +
                "Top 3: María 1350\n" +
                "Top 4: Violeta 1340 puntos.\n" +
                "Top 5: Eric 333 puntos.\n" +
                "Top 6: -------------\n" +
                "Top 7: -------------\n";
        try {
            String response = ai.getLLMResponse(text);
			System.out.println("Respuesta: " + response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
