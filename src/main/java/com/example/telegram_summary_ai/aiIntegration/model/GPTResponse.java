package com.example.telegram_summary_ai.aiIntegration.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GPTResponse {

    private String id;
    private String object;
    private long created;
    private String model;
    private List<Choice> choices;
    private Usage usage;
    private String systemFingerprint;

    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choice {
        private int index;
        private Message message;
        private String finishReason;

    }

    @Setter
    @Getter
    public static class Message {
        private String role;
        private String content;
        private String refusal;

    }

    @Setter
    @Getter
    public static class Usage {
        private int promptTokens;
        private int completionTokens;
        private int totalTokens;
        private TokenDetails promptTokensDetails;
        private TokenDetails completionTokensDetails;

    }

    @Setter
    @Getter
    public static class TokenDetails {
        private int cachedTokens;
        private int reasoningTokens;

    }
}
