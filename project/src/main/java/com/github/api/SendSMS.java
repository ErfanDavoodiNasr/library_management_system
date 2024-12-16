package com.github.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import static com.github.privacy.SendSMSKeys.*;
import static com.github.util.Help.generateRandomCode;

public class SendSMS {

    public static String[] sendSms(String phoneNumber) {
        String randomCode = generateRandomCode();
        HttpResponse<String> response = null;
        try {
            Map<String, Object> requestBody = Map.of(
                    "mobile", phoneNumber,
                    "templateId", TEMPLATE_ID,
                    "parameters", new Map[]{Map.of("name", "CODE", "value", randomCode)}
            );
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .header("Content-Type", "application/json")
                    .header("Accept", "text/plain")
                    .header("x-api-key", API_KEY)
                    .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String[]{randomCode, String.valueOf(response.statusCode()), response.body()};
    }
}


