package com.example.cto.kafka;

import com.example.cto.dto.RequestCreate;
import com.example.cto.dto.RequestCreateWithUser;
import com.example.cto.service.RequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RequestConsumer {

    private final RequestService requestService;
    private final ObjectMapper objectMapper;
    @KafkaListener(topics = "request-create-topic", groupId = "request-group")
    public void consume(String message) {
        try {
            RequestCreateWithUser requestCreate = objectMapper.readValue(message, RequestCreateWithUser.class);
            log.info("Consumed message from topic");
            requestService.createRequest(requestCreate);
        } catch (JsonProcessingException e) {
            // Handle malformed JSON or logging
            log.error("Failed to deserialize Kafka message: {}", message, e);
        }
    }

}

