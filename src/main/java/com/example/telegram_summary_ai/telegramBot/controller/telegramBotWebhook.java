package com.example.telegram_summary_ai.telegramBot.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@RestController
public class telegramBotWebhook {

    private final List<Message> messageHistory = new LinkedList<>();

    @PostMapping("/webhook-path")
    public void onUpdateReceived(@RequestBody Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String text = message.getText();

            saveMessage(message);

            if (text.startsWith("!last")) {

                int numMessages = getNumberFromCommand(text);
                String lastMessages = getLastMessages(numMessages);

                System.out.println(lastMessages);

                // TODO se llama la funcion que abre el melon de la IA :)
                //String iaResult = getLLMResponse(lastMessages);

                // sendTextMessage(chatId, iaResult);
            }
        }

    }

    private String getLastMessages(int numMessages) {
        StringBuilder result = new StringBuilder();
        int startIndex = Math.max(0, messageHistory.size() - numMessages);

        for (int i = startIndex; i < messageHistory.size(); i++) {
            Message msg = messageHistory.get(i);
            String username = "";
            if (msg.getFrom().getFirstName() != null) {
                username = msg.getFrom().getFirstName();
            } else {
                username = "Unknown";
            }

            // Obtener la hora
            int unixTimestamp = msg.getDate();
            Instant messageTime = Instant.ofEpochSecond(unixTimestamp);
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(messageTime, ZoneId.systemDefault());
            String formattedDate = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(zonedDateTime);

            // Agregar el nombre, la hora y el mensaje al resultado
            result.append(formattedDate)
                    .append(" ")
                    .append(username)
                    .append(": ")
                    .append(msg.getText())
                    .append("\n");
        }

        return result.toString();
    }

    private int getNumberFromCommand(String text) {
        try {
            return Integer.parseInt(text.split(" ")[1]);
        } catch (Exception e) {
            return 10;
        }
    }

    private void saveMessage(Message message) {
        if (messageHistory.size() >= 50) {
            messageHistory.remove(0);
        }
        messageHistory.add(message);
    }
}
