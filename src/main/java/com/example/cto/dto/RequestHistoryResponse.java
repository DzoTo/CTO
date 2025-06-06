package com.example.cto.dto;

import java.time.LocalDateTime;

public record RequestHistoryResponse(
        String status,
        String workerName,
        String message,
        LocalDateTime updatedAt
) {
}
