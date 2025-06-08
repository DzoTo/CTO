package com.example.cto.kafka;

import com.example.cto.dto.RequestCreate;
import com.example.cto.dto.RequestCreateWithUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendRequest(RequestCreateWithUser requestCreateWithUser) {
        try {
            String json = objectMapper.writeValueAsString(requestCreateWithUser);
            kafkaTemplate.send("request-create-topic", json);
        } catch (JsonProcessingException e) {
            // Log it or rethrow as a runtime exception
            log.error("Failed to serialize RequestCreateWithUser to JSON", e);
            throw new RuntimeException(e);
        }
    }

}

