package com.example.telegram_summary_ai.telegramBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.springframework.stereotype.Component;


@Component
public class MyTelegramBot extends TelegramLongPollingBot  {

    @Override
    public String getBotUsername() {
        return "ChatSummaryAI_bot";
    }

    @Override
    public String getBotToken() {
        return "your_bot_token_here";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();

            if (text.startsWith("!last")) {
                // Procesa el comando "!last X"
                sendTextMessage(chatId, "Procesando el comando !last");
            }
        }
    }

    private void sendTextMessage(String chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message); // Enviar el mensaje
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}