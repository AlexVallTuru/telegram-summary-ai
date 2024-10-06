package com.example.telegram_summary_ai.aiIntegration.client;

import lombok.Getter;

@Getter
public class ApiException extends Exception {

    private final int statusCode;

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
