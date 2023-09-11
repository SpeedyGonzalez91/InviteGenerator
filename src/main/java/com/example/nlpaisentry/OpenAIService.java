package com.example.nlpaisentry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class OpenAIService {

    private final HttpClient httpClient;

    public OpenAIService() {
        this.httpClient = HttpClients.createDefault();
    }

    public String generateInvitation(InvitationData invitationData) {
        try {
            String apiUrl = "https://api.openai.com/v1/chat/completions";
            HttpPost httpPost = new HttpPost(apiUrl);
            String apiKey = "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"; // Replace with your actual API key

            httpPost.setHeader("Authorization", "Bearer " + apiKey);
            httpPost.setHeader("Content-Type", "application/json");

            // Customize the prompt based on the invitationData
            String prompt = "Generate an invitation for " +
                    invitationData.getName() + " for " +
                    invitationData.getReason() + " at " +
                    invitationData.getLocation() +
                    " with the following instructions: " +
                    invitationData.getInstruction() +
                    ". Please generate a meaningful invitation.";

            JSONObject requestBody = new JSONObject();
            requestBody.put("prompt", prompt);
            // Add any other parameters you need to the request body

            httpPost.setEntity(new StringEntity(requestBody.toString()));

            HttpResponse response = httpClient.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            JSONObject jsonResponse = new JSONObject(responseBody);

            JSONObject invitationDataResponse = jsonResponse.getJSONObject("InvitationData");

            if (invitationDataResponse != null) {
                // Assuming "message" is a field within "InvitationData," replace it with the actual field name
                String message = invitationDataResponse.getString("message");

                if (message != null) {
                    return message.trim();
                }
            }
            return extractAIResponseContent(responseBody);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Error: Invitation data not available";
    }
    private String extractAIResponseContent(String responseBody) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Parse the JSON response body to extract AI response content
        ObjectNode responseNode = objectMapper.readValue(responseBody, ObjectNode.class);
        ArrayNode choicesArray = responseNode.get("").isArray() ? (ArrayNode) responseNode.get("InvitationData") : null;
        if (choicesArray != null && !choicesArray.isEmpty()) {
            return choicesArray.get(0).get("message").get("content").asText();
        } else {
            throw new RuntimeException("Error: AI response is missing or empty.");
        }
    }
}

