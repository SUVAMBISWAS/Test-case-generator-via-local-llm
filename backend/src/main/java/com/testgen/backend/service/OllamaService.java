package com.testgen.backend.service;

import com.testgen.backend.model.TestSuite;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;

@Service
public class OllamaService {

    private final String OLLAMA_URL = "http://localhost:11434/api/generate";
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(com.fasterxml.jackson.databind.MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    public TestSuite generateTestCases(String requirement) {
        try {
            String prompt = "Generate a test suite for the following requirement: " + requirement
                    + ". Output MUST be valid JSON matching this schema: { \"test_suite_name\": \"string\", \"cases\": [ { \"id\": \"string\", \"description\": \"string\", \"steps\": [\"string\"], \"expected\": \"string\" } ] }. "
                    + "IMPORTANT: Use the exact key 'expected' for the expected result. Do NOT use 'expected_result' or 'result'.";

            ObjectNode requestJson = objectMapper.createObjectNode();
            requestJson.put("model", "gemma3:1b");
            requestJson.put("prompt", prompt);
            requestJson.put("stream", false);
            requestJson.put("format", "json");

            String requestBody = objectMapper.writeValueAsString(requestJson);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(OLLAMA_URL, HttpMethod.POST, entity, String.class);

            if (response.getBody() != null) {
                // Parse the outer JSON from Ollama
                JsonNode outerRoot = objectMapper.readTree(response.getBody());
                if (outerRoot.has("response")) {
                    String innerJson = outerRoot.get("response").asText();
                    System.out.println("DEBUG: Raw model response: " + innerJson); // Log for debugging

                    // Sanitize keys to handle common LLM deviations
                    innerJson = innerJson.replaceAll(
                            "\"(expected_result|expectedResult|result|Expected Result|Expected|expected_output|Expected Output)\"\\s*:",
                            "\"expected\":");
                    System.out.println("DEBUG: Sanitized JSON: " + innerJson);

                    // Parse the inner JSON which contains the TestSuite
                    return objectMapper.readValue(innerJson, TestSuite.class);
                }
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Empty response from Ollama");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate test cases", e);
        }
    }
}
