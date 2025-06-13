package com.example.cto.dto;

import com.example.cto.model.enums.RequestStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RequestResponse(Long id,
                              String title,
                              String description,
                              RequestStatus status,
                              LocalDateTime createdAt) {
}
