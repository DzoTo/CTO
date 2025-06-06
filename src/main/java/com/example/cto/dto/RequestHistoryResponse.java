package com.example.cto.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RequestHistoryResponse(
        String status,
        String workerName,
        String message,
        LocalDateTime updatedAt
) {
}
