package com.example.telegram_summary_ai;

import com.example.telegram_summary_ai.aiIntegration.service.AiIntegration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TelegramSummaryAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramSummaryAiApplication.class, args);
	}

}
